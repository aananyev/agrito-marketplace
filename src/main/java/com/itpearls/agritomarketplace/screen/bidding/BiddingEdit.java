package com.itpearls.agritomarketplace.screen.bidding;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.Counterparty;
import io.jmix.core.EntityStates;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Bidding;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@UiController("Bidding.edit")
@UiDescriptor("bidding-edit.xml")
@EditedEntityContainer("biddingDc")
public class BiddingEdit extends StandardEditor<Bidding> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private DateField<Date> dateProposalDateField;
    @Autowired
    private EntityPicker<Counterparty> couterpartyField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            dateProposalDateField.setValue(new Date());
            couterpartyField.setValue(AgritoGlobalValue.counterparty);
        }
    }
}