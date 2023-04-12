package com.itpearls.agritomarketplace.screen.myhousehold;

import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.MyHousehold;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("SelectMyHousehold.browse")
@UiDescriptor("select-my-household-browse.xml")
@LookupComponent("myHouseholdsTable")
public class SelectMyHouseholdBrowse extends StandardLookup<MyHousehold> {
    @Autowired
    private CollectionLoader<MyHousehold> myHouseholdsDl;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        myHouseholdsDl.setParameter("owner", currentAuthentication.getUser());
        myHouseholdsDl.load();
    }
}