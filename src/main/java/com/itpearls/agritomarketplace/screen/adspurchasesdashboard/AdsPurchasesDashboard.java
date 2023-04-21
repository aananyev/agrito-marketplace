package com.itpearls.agritomarketplace.screen.adspurchasesdashboard;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.*;
import com.itpearls.agritomarketplace.screen.dealrequestsaleoffer.DealRequestSaleOfferEdit;
import com.itpearls.agritomarketplace.screen.lottobuy.LotToBuyEdit;
import com.itpearls.agritomarketplace.screen.productbyer.ProductByerEdit;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@UiController("AdsPurchasesDashboard")
@UiDescriptor("ads-purchases-dashboard.xml")
public class AdsPurchasesDashboard extends Screen {
    @Autowired
    private FlowBoxLayout dashboard;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private Notifications notifications;
    private BigDecimal reservedAmount;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        List<LotToBuy> lotToBuys = dataManager.load(LotToBuy.class)
                .all()
                .list();
        for (LotToBuy lotToBuy : lotToBuys) {
            VBoxLayout retBox = createLotCard(lotToBuy);

            dashboard.add(retBox);
        }
    }

    private VBoxLayout createLotCard(LotToBuy lotToBuy) {
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);

        VBoxLayout retBox = uiComponents.create(VBoxLayout.class);
        retBox.setStyleName("card");
        retBox.setWidthAuto();
        retBox.setHeightAuto();
        retBox.setMargin(true);
        retBox.setSpacing(true);

        LinkButton lotName = uiComponents.create(LinkButton.class);
        lotName.setCaption(lotToBuy.getLotArticle());
        lotName.setStyleName("italic");
        lotName.addClickListener(clickEvent -> {
            screenBuilders.editor(LotToBuy.class, this)
                    .withScreenClass(LotToBuyEdit.class)
                    .editEntity(lotToBuy)
                    .build()
                    .show();
        });

        HBoxLayout productBuyerHBox = uiComponents.create(HBoxLayout.class);
        productBuyerHBox.setSpacing(true);
        productBuyerHBox.setWidthFull();

        LinkButton buyer = uiComponents.create(LinkButton.class);
        buyer.setCaption(lotToBuy.getProductBuyer().getCounterpartyName());
        buyer.setStyleName("bold");
        buyer.setAlignment(Component.Alignment.MIDDLE_LEFT);
        buyer.addClickListener(clickEvent -> {
            screenBuilders.editor(ProductByer.class, this)
                    .withScreenClass(ProductByerEdit.class)
                    .editEntity((ProductByer) lotToBuy.getProductBuyer())
                    .build()
                    .show();
        });

        productBuyerHBox.add(buyer);
        productBuyerHBox.expand(buyer);

        BigDecimal averageRating = averageRating((ProductByer) lotToBuy.getProductBuyer());

        if (averageRating != null) {
            Label rating = uiComponents.create(Label.class);
            rating.setValue("(" + averageRating + ")");
            rating.setDescription(messageBundle.getMessage("msgRating"));
            rating.setDescription(messageBundle.getMessage("msgRating")
                    + ": "
                    + nf.format(averageRating));
            rating.setAlignment(Component.Alignment.MIDDLE_RIGHT);
            rating.setWidthFull();
            productBuyerHBox.add(rating);
        }

        VBoxLayout dataHBox = uiComponents.create(VBoxLayout.class);
        dataHBox.setSpacing(true);
        dataHBox.setMargin(true);
        dataHBox.setHeightAuto();
        dataHBox.setWidth(String.valueOf(productBuyerHBox.getWidth()) + "%");
        dataHBox.setStyleName("card");

        Label product = uiComponents.create(Label.class);
        product.setValue(lotToBuy.getProduct().getProductName());

        HBoxLayout amountHBox = uiComponents.create(HBoxLayout.class);
        amountHBox.setSpacing(true);
        amountHBox.setWidthFull();

        Label amountTitle = uiComponents.create(Label.class);
        amountTitle.setValue(messageBundle.getMessage("msgAmount"));
        amountTitle.setAlignment(Component.Alignment.MIDDLE_LEFT);
        amountTitle.setWidthFull();
        amountHBox.add(amountTitle);

        Label amountData = uiComponents.create(Label.class);
        amountData.setValue(lotToBuy.getProductAmount()
                + " "
                + lotToBuy.getUnitMeasurment().getNameUnit());
        amountData.setDescription(nf.format(lotToBuy.getProductAmount()));
        amountData.setAlignment(Component.Alignment.MIDDLE_RIGHT);
        amountData.setWidthAuto();
        amountHBox.add(amountData);
        amountHBox.expand(amountTitle);

        HBoxLayout freeAmountHBox = getFreeAmountHBoxLayout(lotToBuy);
        HBoxLayout reservedAmount = getReservedAmountHBoxLayout(lotToBuy);

        Label price = uiComponents.create(Label.class);
        price.setValue(messageBundle.getMessage("msgCostFor")
                + " "
                + lotToBuy.getUnitMeasurment().getNameUnit()
                + ": "
                + lotToBuy.getPrice()
                + " "
                + messageBundle.getMessage("msgRub"));
        price.setDescription(String.valueOf(lotToBuy.getPrice()));

        HBoxLayout totalHBox = uiComponents.create(HBoxLayout.class);
        totalHBox.setWidthFull();
        totalHBox.setSpacing(true);

        Label totalTitle = uiComponents.create(Label.class);
        totalTitle.setValue(messageBundle.getMessage("msgTotalCost"));
        totalTitle.setStyleName("bold");
        totalTitle.setAlignment(Component.Alignment.MIDDLE_LEFT);
        totalTitle.setWidthFull();
        totalHBox.add(totalTitle);
        totalHBox.expand(totalTitle);

        Label total = uiComponents.create(Label.class);
        total.setValue(String.format("%.2f",
                lotToBuy.getPrice().doubleValue() * lotToBuy.getProductAmount().doubleValue())
                + " "
                + messageBundle.getMessage("msgRub"));
        total.setStyleName("bold");
        total.setAlignment(Component.Alignment.MIDDLE_RIGHT);
        total.setWidthAuto();
        totalHBox.add(total);

        Button buttonSale = uiComponents.create(Button.class);
        buttonSale.setCaption(messageBundle.getMessage("msgPropose"));
        buttonSale.setAlignment(Component.Alignment.BOTTOM_RIGHT);
        buttonSale.addClickListener(clickEvent -> {
            if (!AgritoGlobalValue.counterparty.equals(lotToBuy.getProductBuyer())) {
                screenBuilders.editor(DealRequestSaleOffer.class, this)
                        .withScreenClass(DealRequestSaleOfferEdit.class)
                        .newEntity()
                        .withInitializer(e -> {
                            e.setLotForBuy(lotToBuy);
                        })
                        .build()
                        .show();
            } else {
                notifications.create(Notifications.NotificationType.ERROR)
                        .withCaption(messageBundle.getMessage("msgError"))
                        .withDescription(messageBundle.getMessage("msgErrorDealWithYourself"))
                        .show();
            }
        });

        dataHBox.add(amountHBox);
        dataHBox.add(freeAmountHBox);
        if (reservedAmount != null) {
            dataHBox.add(reservedAmount);
        }
        dataHBox.add(price);
        dataHBox.add(totalHBox);
        dataHBox.expand(totalHBox);

        retBox.add(lotName);
        retBox.add(productBuyerHBox);
        retBox.add(product);
        retBox.add(dataHBox);
        retBox.expand(dataHBox);

        retBox.add(buttonSale);

        return retBox;
    }

    private HBoxLayout getFreeAmountHBoxLayout(LotToBuy lotForSell) {
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);

        HBoxLayout freeAmountHBox = uiComponents.create(HBoxLayout.class);
        freeAmountHBox.setSpacing(true);
        freeAmountHBox.setWidthFull();

        Label freeAmountTitle = uiComponents.create(Label.class);
        freeAmountTitle.setValue(messageBundle.getMessage("msgFreeAmount"));
        freeAmountTitle.setAlignment(Component.Alignment.MIDDLE_LEFT);
        freeAmountTitle.setWidthFull();
        freeAmountHBox.add(freeAmountTitle);

        Label freeAmountData = uiComponents.create(Label.class);
        freeAmountData.setValue(getFreeAmount(lotForSell)
                + " "
                + lotForSell.getUnitMeasurment().getNameUnit());
        freeAmountData.setDescription(nf.format(lotForSell.getProductAmount()));
        freeAmountData.setAlignment(Component.Alignment.MIDDLE_RIGHT);
        freeAmountData.setWidthAuto();
        freeAmountHBox.add(freeAmountData);
        freeAmountHBox.expand(freeAmountTitle);

        return freeAmountHBox;
    }

    private HBoxLayout getReservedAmountHBoxLayout(LotToBuy lotToBuy) {

        reservedAmount = getReservedAmount(lotToBuy);

        if (reservedAmount != null) {
            RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                    RuleBasedNumberFormat.SPELLOUT);

            HBoxLayout retHBox = uiComponents.create(HBoxLayout.class);
            retHBox.setSpacing(true);
            retHBox.setWidthFull();

            Label reservedAmountTitle = uiComponents.create(Label.class);
            reservedAmountTitle.setValue(messageBundle.getMessage("msgReservedAmount"));
            reservedAmountTitle.setAlignment(Component.Alignment.MIDDLE_LEFT);
            reservedAmountTitle.setWidthFull();
            retHBox.add(reservedAmountTitle);

            Label reservedAmountData = uiComponents.create(Label.class);
            reservedAmountData.setValue(reservedAmount
                    + " "
                    + lotToBuy.getUnitMeasurment().getNameUnit());
            reservedAmountData.setDescription(nf.format(lotToBuy.getProductAmount()));
            reservedAmountData.setAlignment(Component.Alignment.MIDDLE_RIGHT);
            reservedAmountData.setWidthAuto();
            retHBox.add(reservedAmountData);
            retHBox.expand(reservedAmountTitle);

            return retHBox;
        } else {
            return null;
        }
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

    private Object getFreeAmount(LotToBuy lotToBuy) {
        String freeAmount = "";
        BigDecimal reserved = getReservedAmount(lotToBuy);

        if (reserved != null) {
            freeAmount = lotToBuy.getProductAmount().subtract(reserved).toString();
        } else {
            freeAmount = lotToBuy.getProductAmount().toString();
        }

        return freeAmount;
    }

    private BigDecimal averageRating(ProductByer productByer) {
        String QUERY_RATING = "select avg(e.rating) from CounterpartyRating e where e.counterparty = :counterparty";

        return dataManager.loadValue(QUERY_RATING, BigDecimal.class)
                .parameter("counterparty", productByer)
                .one();
    }
}