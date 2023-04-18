package com.itpearls.agritomarketplace.screen.adssaledashboard;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.*;
import com.itpearls.agritomarketplace.screen.agriculturalmanufacturer.AgriculturalManufacturerEdit;
import com.itpearls.agritomarketplace.screen.dealrequestpurchasebuy.DealRequestPurchaseBuyEdit;
import com.itpearls.agritomarketplace.screen.dealrequestsaleoffer.DealRequestSaleOfferEdit;
import com.itpearls.agritomarketplace.screen.lotforsell.LotForSellEdit;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@UiController("AdsSaleDashboard")
@UiDescriptor("ads-sale-dashboard.xml")
public class AdsSaleDashboard extends Screen {
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
        List<LotForSell> lotForSelList = dataManager.load(LotForSell.class)
                .all()
                .list();
        for (LotForSell lotForSell : lotForSelList) {
            VBoxLayout retBox = createLotCard(lotForSell);

            dashboard.add(retBox);
        }
    }

    private VBoxLayout createLotCard(LotForSell lotForSell) {
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);

        VBoxLayout retBox = uiComponents.create(VBoxLayout.class);
        retBox.setStyleName("card");
        retBox.setWidthAuto();
        retBox.setHeightAuto();
        retBox.setMargin(true);
        retBox.setSpacing(true);

        LinkButton lotName = uiComponents.create(LinkButton.class);
        lotName.setCaption(lotForSell.getLotArticle());
        lotName.setStyleName("italic");
        lotName.addClickListener(clickEvent -> {
            screenBuilders.editor(LotForSell.class, this)
                    .withScreenClass(LotForSellEdit.class)
                    .editEntity(lotForSell)
                    .build()
                    .show();
        });

        HBoxLayout manufacturerHBox = uiComponents.create(HBoxLayout.class);
        manufacturerHBox.setSpacing(true);
        manufacturerHBox.setWidthFull();

        LinkButton manufacturer = uiComponents.create(LinkButton.class);
        manufacturer.setCaption(lotForSell.getAgriculturalManufacturer().getCounterpartyName());
        manufacturer.setStyleName("bold");
        manufacturer.setAlignment(Component.Alignment.MIDDLE_LEFT);
        manufacturer.addClickListener(clickEvent -> {
            screenBuilders.editor(AgriculturalManufacturer.class, this)
                    .withScreenClass(AgriculturalManufacturerEdit.class)
                    .editEntity((AgriculturalManufacturer) lotForSell.getAgriculturalManufacturer())
                    .build()
                    .show();
        });

        manufacturerHBox.add(manufacturer);
        manufacturerHBox.expand(manufacturer);

        BigDecimal averageRating = averageRating((AgriculturalManufacturer) lotForSell.getAgriculturalManufacturer());

        if (averageRating != null) {
            Label rating = uiComponents.create(Label.class);
            rating.setValue("(" + averageRating + ")");
            rating.setDescription(messageBundle.getMessage("msgRating"));
            rating.setDescription(messageBundle.getMessage("msgRating")
                    + ": "
                    + nf.format(averageRating));
            rating.setAlignment(Component.Alignment.MIDDLE_RIGHT);
            rating.setWidthFull();
            manufacturerHBox.add(rating);
        }

        VBoxLayout dataHBox = uiComponents.create(VBoxLayout.class);
        dataHBox.setSpacing(true);
        dataHBox.setMargin(true);
        dataHBox.setHeightAuto();
        dataHBox.setWidth(String.valueOf(manufacturerHBox.getWidth()) + "%");
        dataHBox.setStyleName("card");

        Label product = uiComponents.create(Label.class);
        product.setValue(lotForSell.getProduct().getProductName());

        HBoxLayout amountHBox = uiComponents.create(HBoxLayout.class);
        amountHBox.setSpacing(true);
        amountHBox.setWidthFull();

        Label amountTitle = uiComponents.create(Label.class);
        amountTitle.setValue(messageBundle.getMessage("msgAmount"));
        amountTitle.setAlignment(Component.Alignment.MIDDLE_LEFT);
        amountTitle.setWidthFull();
        amountHBox.add(amountTitle);

        Label amountData = uiComponents.create(Label.class);
        amountData.setValue(lotForSell.getProductAmount()
                + " "
                + lotForSell.getUnitMeasurment().getNameUnit());
        amountData.setDescription(nf.format(lotForSell.getProductAmount()));
        amountData.setAlignment(Component.Alignment.MIDDLE_RIGHT);
        amountData.setWidthAuto();
        amountHBox.add(amountData);
        amountHBox.expand(amountTitle);

        HBoxLayout freeAmountHBox = getFreeAmountHBoxLayout(lotForSell);
        HBoxLayout reservedAmount = getReservedAmountHBoxLayout(lotForSell);

        Label price = uiComponents.create(Label.class);
        price.setValue(messageBundle.getMessage("msgCostFor")
                + " "
                + lotForSell.getUnitMeasurment().getNameUnit()
                + ": "
                + lotForSell.getPrice()
                + " "
                + messageBundle.getMessage("msgRub"));
        price.setDescription(String.valueOf(lotForSell.getPrice()));

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
        total.setValue(lotForSell.getPrice().doubleValue() * lotForSell.getProductAmount().doubleValue()
                + messageBundle.getMessage("msgRub"));
        total.setStyleName("bold");
        total.setAlignment(Component.Alignment.MIDDLE_RIGHT);
        total.setWidthAuto();
        totalHBox.add(total);

        Button buttonBuy = uiComponents.create(Button.class);
        buttonBuy.setCaption(messageBundle.getMessage("msgBuy"));
        buttonBuy.setAlignment(Component.Alignment.BOTTOM_RIGHT);
        buttonBuy.addClickListener(clickEvent -> {
            if (!AgritoGlobalValue.counterparty.equals(lotForSell.getAgriculturalManufacturer())) {
                screenBuilders.editor(DealRequestPurchaseBuy.class, this)
                        .withScreenClass(DealRequestPurchaseBuyEdit.class)
                        .newEntity()
                        .withInitializer(e -> {
                            e.setLotForSell(lotForSell);
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
        retBox.add(manufacturerHBox);
        retBox.add(product);
        retBox.add(dataHBox);
        retBox.expand(dataHBox);

        retBox.add(buttonBuy);

        return retBox;
    }

    private HBoxLayout getFreeAmountHBoxLayout(LotForSell lotForSell) {
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

    private HBoxLayout getReservedAmountHBoxLayout(LotForSell lotForSell) {

        reservedAmount = getReservedAmount(lotForSell);

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
                    + lotForSell.getUnitMeasurment().getNameUnit());
            reservedAmountData.setDescription(nf.format(lotForSell.getProductAmount()));
            reservedAmountData.setAlignment(Component.Alignment.MIDDLE_RIGHT);
            reservedAmountData.setWidthAuto();
            retHBox.add(reservedAmountData);
            retHBox.expand(reservedAmountTitle);

            return retHBox;
        } else {
            return null;
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

    private Object getFreeAmount(LotForSell lotForSell) {
        String freeAmount = "";
        BigDecimal reserved = getReservedAmount(lotForSell);

        if (reserved != null) {
            freeAmount = lotForSell.getProductAmount().subtract(reserved).toString();
        } else {
            freeAmount = lotForSell.getProductAmount().toString();
        }

        return freeAmount;
    }

    private BigDecimal averageRating(AgriculturalManufacturer agriculturalManufacturer) {
        String QUERY_RATING = "select avg(e.rating) from CounterpartyRating e where e.counterparty = :counterparty";

        return dataManager.loadValue(QUERY_RATING, BigDecimal.class)
                .parameter("counterparty", agriculturalManufacturer)
                .one();
    }

}