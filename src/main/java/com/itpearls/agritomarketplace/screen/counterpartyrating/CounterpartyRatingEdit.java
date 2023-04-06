package com.itpearls.agritomarketplace.screen.counterpartyrating;

import com.itpearls.agritomarketplace.entity.Counterparty;
import com.itpearls.agritomarketplace.entity.User;
import io.jmix.core.EntityStates;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.session.SessionData;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.core.usersubstitution.UserSubstitutionManager;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.CounterpartyRating;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@UiController("CounterpartyRating.edit")
@UiDescriptor("counterparty-rating-edit.xml")
@EditedEntityContainer("counterpartyRatingDc")
public class CounterpartyRatingEdit extends StandardEditor<CounterpartyRating> {
    @Autowired
    private EntityPicker<Counterparty> counterpartyField;
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private EntityPicker<User> userField;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private DateField<Date> ratingDate;

    public void setCounterperty(Counterparty counterparty) {
        counterpartyField.setValue(counterparty);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            userField.setValue((User) currentAuthentication.getUser());
            ratingDate.setValue(new Date());
        }
    }
}