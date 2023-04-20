package com.itpearls.agritomarketplace.screen.counterparty;

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
import com.itpearls.agritomarketplace.entity.Counterparty;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@UiController("Counterparty.browse")
@UiDescriptor("counterparty-browse.xml")
@LookupComponent("counterpartiesTable")
public class CounterpartyBrowse extends StandardLookup<Counterparty> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private GroupTable<Counterparty> counterpartiesTable;
    @Autowired
    private DataManager dataManager;

    @Install(to = "counterpartiesTable.actionMenu", subject = "columnGenerator")
    private Component counterpartiesTableActionMenuColumnGenerator(Counterparty counterparty) {
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
                    event.setCounterparty(counterpartiesTable.getSingleSelected());
                })
                .newEntity()
                .build()
                .show();
    }

    @Install(to = "counterpartiesTable.rating", subject = "columnGenerator")
    private Component counterpartiesTableRatingColumnGenerator(Counterparty counterparty) {
        String QUERY_RATING = "select avg(e.rating) from CounterpartyRating e where e.counterparty = :counterparty";

        BigDecimal averageRating = dataManager.loadValue(QUERY_RATING, BigDecimal.class)
                .parameter("counterparty", counterparty)
                .one();

        Label label = uiComponents.create(Label.class);
        label.setValue(averageRating);
        label.setWidthFull();
        label.setAlignment(Component.Alignment.MIDDLE_CENTER);

        return label;
    }
}