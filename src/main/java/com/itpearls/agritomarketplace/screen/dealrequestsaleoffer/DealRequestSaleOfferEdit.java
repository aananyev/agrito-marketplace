package com.itpearls.agritomarketplace.screen.dealrequestsaleoffer;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.EntityStates;
import io.jmix.core.Metadata;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

@UiController("DealRequestSaleOffer.edit")
@UiDescriptor("deal-request-sale-offer-edit.xml")
@EditedEntityContainer("dealRequestSaleOfferDc")
public class DealRequestSaleOfferEdit extends StandardEditor<DealRequestSaleOffer> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private EntityPicker<Counterparty> productSellerField;
    @Autowired
    private EntityPicker<Counterparty> productBuyerField;
    @Autowired
    private EntityPicker<LotToBuy> lotForBuyField;
    @Autowired
    private TextField<BigDecimal> amountField;
    @Autowired
    private TextField proposalCostField;
    @Autowired
    private ComboBox<DealRequestStatus> dealRequestStatusField;
    @Autowired
    private Metadata metadata;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionLoader<Bidding> biddingDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            productSellerField.setValue(AgritoGlobalValue.counterparty);
            productBuyerField.setValue(lotForBuyField.getValue().getProductBuyer());
            dealRequestStatusField.setValue(DealRequestStatus.NEW);
        }

        biddingDl.setParameter("tradingLot", getEditedEntity().getLotForBuy());
        biddingDl.load();
    }

    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {
        createBiddingRecord(event);
    }

    private void createBiddingRecord(BeforeCloseEvent event) {
        if (!event.isClosePrevented()) {
            Bidding bidding = metadata.create(Bidding.class);

            bidding.setTradingLot(getEditedEntity().getLotForBuy());
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

            if (lotForBuyField.getValue().getPrice().equals(getEditedEntity().getProposalCost())) {
                dealRequestStatusField.setValue(DealRequestStatus.AGREEMENT);
            } else {
                dealRequestStatusField.setValue(DealRequestStatus.COUNTER_OFFER);
            }
        }
    }

    @Subscribe("lotForBuyField")
    public void onLotForBuyFieldValueChange(HasValue.ValueChangeEvent<LotToBuy> event) {
        productBuyerField.setValue((ProductByer) event.getValue().getProductBuyer());
        amountField.setValue(getFreeAmount(event.getValue()));
        proposalCostField.setValue(lotForBuyField.getValue().getPrice());
    }

    public void setLotForBuy(LotToBuy lotToBuy) {
        lotForBuyField.setValue(lotToBuy);

        amountField.setValue(lotToBuy.getProductAmount());
        proposalCostField.setValue(lotToBuy.getPrice());
    }

    private BigDecimal getFreeAmount(LotToBuy lotToBuy) {
        BigDecimal freeAmount = BigDecimal.ZERO;
        BigDecimal reserved = getReservedAmount(lotToBuy);

        if (reserved != null) {
            freeAmount = lotToBuy.getProductAmount().subtract(reserved);
        } else {
            freeAmount = lotToBuy.getProductAmount();
        }

        return freeAmount;
    }

    BigDecimal getReservedAmount(LotToBuy lotForSell) {
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
}