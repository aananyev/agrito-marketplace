package com.itpearls.agritomarketplace.screen.bidding;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.UiComponents;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.*;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UiController("Bidding.browse")
@UiDescriptor("bidding-browse.xml")
@LookupComponent("biddingsTable")
public class BiddingBrowse extends StandardLookup<Bidding> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private Metadata metadata;
    @Autowired
    private GroupTable<Bidding> biddingsTable;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionLoader<Bidding> biddingsDl;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private CheckBox onlyMyBiddingsCheckBox;
    @Autowired
    private CollectionContainer<Bidding> biddingsDc;
    @Autowired
    private Notifications notifications;

    @Install(to = "biddingsTable.action", subject = "columnGenerator")
    private Component biddingsTableActionColumnGenerator(Bidding bidding) {
        PopupButton popupButton = uiComponents.create(PopupButton.class);

        popupButton.setIcon(JmixIcon.BARS.iconName());
        popupButton.addAction(new BaseAction("setApproveAction")
                .withCaption(messageBundle.getMessage("msgApprove"))
                .withHandler(actionPerformedEvent -> {
                    createBiddingAction(BiddingStatus.APPROVE, popupButton);
                    popupButton.setEnabled(false);
                }));

        popupButton.addAction(new BaseAction("setRejectAction")
                .withCaption(messageBundle.getMessage("msgReject"))
                .withHandler(actionPerformedEvent -> {
                    createBiddingAction(BiddingStatus.REJECT, popupButton);
                    popupButton.setEnabled(false);
                }));

        popupButton.addAction(new BaseAction("setCounterOfferAction")
                .withCaption(messageBundle.getMessage("msgCounterOffer"))
                .withHandler(actionPerformedEvent -> {
                    createBiddingAction(BiddingStatus.COUNTER_OFFER, popupButton);
                    popupButton.setEnabled(false);
                }));

        if (bidding.getChildBidding() == null) {
            if (bidding.getBiddingStatus() == BiddingStatus.REJECT ||
                    bidding.getBiddingStatus() == BiddingStatus.APPROVE) {
                popupButton.setEnabled(false);
            } else {
                popupButton.setEnabled(true);
            }
        } else {
            popupButton.setEnabled(false);
            if (bidding.getBiddingStatus() == BiddingStatus.REJECT ||
                    bidding.getBiddingStatus() == BiddingStatus.APPROVE) {
                popupButton.setEnabled(false);
            }
        }

        return popupButton;
    }

    private void createBiddingAction(BiddingStatus biddingStatus,
                                     PopupButton popupButton) {
        Bidding biddingNew = metadata.create(Bidding.class);
        Bidding biddingParent = biddingsTable.getSingleSelected();

        biddingNew.setTradingLot(biddingsTable.getSingleSelected().getTradingLot());
        biddingNew.setCouterparty(AgritoGlobalValue.counterparty);
        biddingNew.setBiddingStatus(biddingStatus);
        biddingNew.setParentBidding(biddingParent);
        biddingNew.setAmount(biddingsTable.getSingleSelected().getAmount());
        biddingNew.setDealRequest(biddingParent.getDealRequest());

        switch (biddingStatus) {
            case COUNTER_OFFER:
                biddingNew.getDealRequest().setDealRequestStatus(DealRequestStatus.COUNTER_OFFER);
                break;
            case APPROVE:
                // TODO тут надо уменьшить количество предложения в обьявлении
                biddingNew.setProposalCost(biddingParent.getProposalCost());
                biddingNew.getDealRequest().setDealRequestStatus(DealRequestStatus.AGREEMENT);
                break;
            case REJECT:
                biddingNew.setProposalCost(biddingParent.getProposalCost());
                biddingNew.getDealRequest().setDealRequestStatus(DealRequestStatus.REJECTION);
                break;
            default:
                break;
        }

        screenBuilders.editor(Bidding.class, this)
                .withScreenClass(BiddingEdit.class)
                .withInitializer(e -> {
                    if (biddingStatus == BiddingStatus.APPROVE || biddingStatus == BiddingStatus.REJECT) {
                        e.setProposalCost(biddingParent.getProposalCost());
                        // как то блокировать ввод цены ???
                    }
                })
                .editEntity(biddingNew)
                .withAfterCloseListener(biddingEditAfterScreenCloseEvent -> {
                    popupButton.setEnabled(false);
                    popupButton.setIcon(JmixIcon.CANCEL.source());

                    biddingParent.setChildBidding(biddingNew);
                    dataManager.save(biddingParent);

                    biddingsDl.load();
                    biddingsTable.repaint();
                })
                .withOpenMode(OpenMode.DIALOG)
                .build()
                .show();
    }

    @Install(to = "biddingsTable.statusIcon", subject = "columnGenerator")
    private Component biddingsTableStatusIconColumnGenerator(Bidding bidding) {
        Label retLabel = uiComponents.create(Label.class);
        String iconLabel = "";

        if (bidding.getBiddingStatus() != null) {
            switch (bidding.getBiddingStatus()) {
                case REJECT:
                    iconLabel = JmixIcon.CANCEL.source();
                    break;
                case APPROVE:
                    iconLabel = JmixIcon.HANDSHAKE_O.source();
                    break;
                case COUNTER_OFFER:
                    iconLabel = JmixIcon.ROTATE_LEFT.source();
                    break;
                default:
                    iconLabel = JmixIcon.QUESTION.source();
                    break;
            }
        } else {
            iconLabel = JmixIcon.QUESTION.source();
        }

        retLabel.setIcon(iconLabel);
        return retLabel;
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        onlyMyBiddingsCheckBox.setValue(true);
    }

    @Subscribe("onlyMyBiddingsCheckBox")
    public void onOnlyMyBiddingsCheckBoxValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (onlyMyBiddingsCheckBox.getValue()) {
            biddingsDl.setParameter("owner_seller", AgritoGlobalValue.counterparty);
            biddingsDl.setParameter("owner_buyer", AgritoGlobalValue.counterparty);
        } else {
            biddingsDl.removeParameter("owner_seller");
            biddingsDl.removeParameter("owner_buyer");
        }

        biddingsDl.load();
    }

    @Subscribe("removeCompleted")
    public void onRemoveCompletedValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        notifications.create(Notifications.NotificationType.WARNING)
                .withCaption(messageBundle.getMessage("msgWarning"))
                .withDescription("Тут будет фильтр который удаляет уже согласованные торги")
                .show();
    }

    @Install(to = "biddingsTable.biddingType", subject = "columnGenerator")
    private Component biddingsTableBiddingTypeColumnGenerator(Bidding bidding) {
        Label retLabel = uiComponents.create(Label.class);

        retLabel.setWidthAuto();
        retLabel.setValue(bidding.getTradingLot().getTradingLotType());

        return retLabel;
    }

    @Install(to = "biddingsTable.counterpartyDealInitiator", subject = "columnGenerator")
    private Component biddingsTableCounterpartyDealInitiatorColumnGenerator(Bidding bidding) {
        Label retLabel = uiComponents.create(Label.class);

        if (bidding.getTradingLot().getTradingLotType() != null) {
            if (bidding.getTradingLot().getTradingLotType().equals(TradingLotType.BUY)) {
                retLabel.setValue(bidding.getTradingLot().getProductBuyer().getCounterpartyName());
            } else {
                retLabel.setValue(bidding.getTradingLot().getAgriculturalManufacturer().getCounterpartyName());
            }
        } else {
            if (bidding.getTradingLot().getAgriculturalManufacturer() != null) {
                retLabel.setValue(bidding.getTradingLot().getAgriculturalManufacturer().getCounterpartyName());
            } else {
                if (bidding.getTradingLot().getProductBuyer() != null) {
                    retLabel.setValue(bidding.getTradingLot().getProductBuyer().getCounterpartyName());
                }
            }
        }

        return retLabel;
    }
}