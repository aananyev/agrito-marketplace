package com.itpearls.agritomarketplace.screen.adssaledashboard;

import com.itpearls.agritomarketplace.entity.AgriculturalManufacturer;
import com.itpearls.agritomarketplace.entity.LotForSell;
import com.itpearls.agritomarketplace.screen.agriculturalmanufacturer.AgriculturalManufacturerEdit;
import com.itpearls.agritomarketplace.screen.lotforsell.LotForSellEdit;
import io.jmix.core.DataManager;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

        LinkButton manufacturer = uiComponents.create(LinkButton.class);
        manufacturer.setCaption(lotForSell.getAgriculturalManufacturer().getCounterpartyName());
        manufacturer.setStyleName("bold");
        manufacturer.addClickListener(clickEvent -> {
            screenBuilders.editor(AgriculturalManufacturer.class, this)
                    .withScreenClass(AgriculturalManufacturerEdit.class)
                    .editEntity(lotForSell.getAgriculturalManufacturer())
                    .build()
                    .show();
        });

        Label product = uiComponents.create(Label.class);
        product.setValue(lotForSell.getProduct().getProductName());

        Label amount = uiComponents.create(Label.class);
        amount.setValue(lotForSell.getProductAmount());

        Label price = uiComponents.create(Label.class);
        price.setValue(messageBundle.getMessage("msgCostFor")
                + " "
                + lotForSell.getUnitMeasurment().getNameUnit()
                + ": "
                + lotForSell.getPrice()
                + messageBundle.getMessage("msgRub"));

        Label total = uiComponents.create(Label.class);
        total.setValue(lotForSell.getPrice() * lotForSell.getProductAmount() + messageBundle.getMessage("msgRub"));
        total.setStyleName("bold");

        Button buttonBuy = uiComponents.create(Button.class);
        buttonBuy.setCaption(messageBundle.getMessage("msgBuy"));
        buttonBuy.setAlignment(Component.Alignment.BOTTOM_RIGHT);

        retBox.add(lotName);
        retBox.add(manufacturer);
        retBox.add(product);
        retBox.add(amount);
        retBox.add(price);
        retBox.add(total);
        retBox.add(buttonBuy);

        return retBox;
    }

}