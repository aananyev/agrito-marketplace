package com.itpearls.agritomarketplace.screen.mytradeorganisation;

import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.MyTradeOrganisation;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("SelectMyTradeOrganisation.browse")
@UiDescriptor("select-my-trade-organisation-browse.xml")
@LookupComponent("myTradeOrganisationsTable")
public class SelectMyTradeOrganisationBrowse extends StandardLookup<MyTradeOrganisation> {
    @Autowired
    private CollectionLoader<MyTradeOrganisation> myTradeOrganisationsDl;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        myTradeOrganisationsDl.setParameter("owner", currentAuthentication.getUser());
        myTradeOrganisationsDl.load();
    }
}