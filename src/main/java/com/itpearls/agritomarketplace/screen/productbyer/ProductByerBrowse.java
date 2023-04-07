package com.itpearls.agritomarketplace.screen.productbyer;

import com.itpearls.agritomarketplace.entity.CounterpartyRating;
import com.itpearls.agritomarketplace.screen.counterpartyrating.CounterpartyRatingEdit;
import io.jmix.core.DataManager;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.UiComponents;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.Label;
import io.jmix.ui.component.PopupButton;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductByer;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@UiController("ProductByer.browse")
@UiDescriptor("product-byer-browse.xml")
@LookupComponent("productByersTable")
public class ProductByerBrowse extends StandardLookup<ProductByer> {
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private GroupTable<ProductByer> productByersTable;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private DataManager dataManager;

    @Install(to = "productByersTable.actionMenu", subject = "columnGenerator")
    private Component productByersTableActionMenuColumnGenerator(ProductByer productByer) {
        PopupButton popupButton = uiComponents.create(PopupButton.class);

        popupButton.setIcon(JmixIcon.BARS.iconName());
        popupButton.setAlignment(Component.Alignment.MIDDLE_CENTER);
        popupButton.addAction(new BaseAction("setRatingAction")
                .withCaption(messageBundle.getMessage("msgSetRating"))
                .withHandler(actionPerformedEvent -> {
                    addRationgForm(actionPerformedEvent);
                }));

        return popupButton;
    }

    private void addRationgForm(Action.ActionPerformedEvent actionPerformedEvent) {
        screenBuilders.editor(CounterpartyRating.class, this)
                .withScreenClass(CounterpartyRatingEdit.class)
                .withInitializer(event -> {
                    event.setCounterparty(productByersTable.getSingleSelected());
                })
                .newEntity()
                .build()
                .show();
    }

    @Install(to = "productByersTable.rating", subject = "columnGenerator")
    private Component productByersTableRatingColumnGenerator(ProductByer productByer) {
        String QUERY_RATING = "select avg(e.rating) from CounterpartyRating e where e.counterparty = :counterparty";

        BigDecimal averageRating = dataManager.loadValue(QUERY_RATING, BigDecimal.class)
                .parameter("counterparty", productByer)
                .one();

        Label label = uiComponents.create(Label.class);
        label.setValue(averageRating);
        label.setWidthFull();
        label.setAlignment(Component.Alignment.MIDDLE_CENTER);

        return label;
    }


}