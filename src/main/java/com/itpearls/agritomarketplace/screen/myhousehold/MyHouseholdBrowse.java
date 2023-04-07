package com.itpearls.agritomarketplace.screen.myhousehold;

import com.itpearls.agritomarketplace.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.session.SessionData;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.MyHousehold;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("MyHousehold.browse")
@UiDescriptor("my-household-browse.xml")
@LookupComponent("myHouseholdsTable")
public class MyHouseholdBrowse extends StandardLookup<MyHousehold> {
    @Autowired
    private CollectionLoader<MyHousehold> myHouseholdsDl;
    @Autowired
    private SessionData sessionData;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        myHouseholdsDl.setParameter("owner",
                (User) currentAuthentication.getUser());
        myHouseholdsDl.load();
    }
}