package com.itpearls.agritomarketplace.screen.dealrequestpurchasebuy;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.EntityStates;
import io.jmix.core.Metadata;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

@UiController("DealRequestPurchaseBuy.edit")
@UiDescriptor("deal-request-purchase-buy-edit.xml")
@EditedEntityContainer("dealRequestPurchaseBuyDc")
public class DealRequestPurchaseBuyEdit extends StandardEditor<DealRequestPurchaseBuy> {
    @Autowired
    private EntityPicker<LotForSell> lotForSellField;
    @Autowired
    private TextField<BigDecimal> amountField;
    @Autowired
    private TextField<BigDecimal> proposalCostField;
    @Autowired
    private EntityPicker<Counterparty> productSellerField;
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private EntityPicker<Counterparty> productBuyerField;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private Metadata metadata;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private DataContext dataContext;
    @Autowired
    private ComboBox<DealRequestStatus> dealRequestStatusField;
    @Autowired
    private Notifications notifications;

    @Install(to = "amountField", subject = "validator")
    private void amountFieldValidator(BigDecimal value) {
        if (amountField.getValue().compareTo(lotForSellField.getValue().getProductAmount()) > 0) {
            throw new ValidationException(messageBundle.getMessage("msgWrongAmount")
                    + " "
                    + lotForSellField.getValue().getProductAmount()
                    + " "
                    + lotForSellField.getValue().getUnitMeasurment().getNameUnit()
                    + " "
                    + lotForSellField.getValue().getProduct().getProductName());
        }
    }

    public void setLotForSell(LotForSell lotForSell) {
        lotForSellField.setValue(lotForSell);

        amountField.setValue(lotForSell.getProductAmount());
        proposalCostField.setValue(lotForSell.getPrice());
    }

    @Subscribe("lotForSellField")
    public void onLotForSellFieldValueChange(HasValue.ValueChangeEvent<LotForSell> event) {
        productSellerField.setValue((AgriculturalManufacturer) event.getValue().getAgriculturalManufacturer());
        amountField.setValue(event.getValue().getProductAmount());
        proposalCostField.setValue(event.getValue().getPrice());
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            productBuyerField.setValue(AgritoGlobalValue.counterparty);
            dealRequestStatusField.setValue(DealRequestStatus.NEW);
        }
    }

    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {
        checkReservedAmount(event);
        createBiddingRecord(event);
    }

    private void checkReservedAmount(BeforeCloseEvent event) {
/*        BigDecimal amount = getEditedEntity().getAmount();
        BigDecimal reservedAmount = getReservedAmount(getEditedEntity().getLotForSell());
        BigDecimal requestAmount = amountField.getValue();
        Integer bool = amount.compareTo(requestAmount.add(reservedAmount)); */

        if (getEditedEntity().getAmount().compareTo(
                (getReservedAmount(getEditedEntity().getLotForSell()).add(amountField.getValue()))) <= 0) {
            notifications.create(Notifications.NotificationType.WARNING)
                    .withCaption(messageBundle.getMessage("msgWarning"))
                    .withDescription(messageBundle.getMessage("msgLessThenReserved"))
                    .show();
        }
    }

    BigDecimal getReservedAmount(LotForSell lotForSell) {
        BigDecimal reserved = BigDecimal.ZERO;

        try {
            reserved = dataManager.loadValue("select sum(e.amount) " +
                            "from Bidding e " +
                            "where e.tradingLot = :tradingLot and e.biddingStatus = :biddingStatus", BigDecimal.class)
                    .parameter("biddingStatus", BiddingStatus.APPROVE)
                    .parameter("tradingLot", lotForSell)
                    .one();
        } catch (NullPointerException e) {
            reserved = BigDecimal.ZERO;
            e.printStackTrace();
        }

        return reserved;
    }

    private void createBiddingRecord(BeforeCloseEvent event) {
        Bidding bidding = metadata.create(Bidding.class);

        bidding.setTradingLot(getEditedEntity().getLotForSell());
        bidding.setBiddingStatus(BiddingStatus.COUNTER_OFFER);
        bidding.setParentBidding(null);
        bidding.setAmount(getEditedEntity().getAmount());
        bidding.setProposalCost(getEditedEntity().getProposalCost());
        bidding.setComment(getEditedEntity().getComment());
        bidding.setDateProposal(new Date());
        bidding.setDealRequest(getEditedEntity());
        bidding.setCouterparty(productBuyerField.getValue());
        bidding.setTradeRole(AgritoGlobalValue.tradeRole);

        dataManager.save(bidding);

        if (lotForSellField.getValue().getPrice().equals(getEditedEntity().getProposalCost())) {
            dealRequestStatusField.setValue(DealRequestStatus.AGREEMENT);
        } else {
            dealRequestStatusField.setValue(DealRequestStatus.COUNTER_OFFER);
        }
    }
}