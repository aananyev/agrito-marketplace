package com.itpearls.agritomarketplace.screen.myhousehold;

import com.itpearls.agritomarketplace.entity.AgriculturalManufacturer;
import com.itpearls.agritomarketplace.entity.TradeRole;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Label;
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
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        myHouseholdsDl.setParameter("owner", currentAuthentication.getUser());
        myHouseholdsDl.load();
    }

    @Install(to = "myHouseholdsTable.counterPartyType", subject = "columnGenerator")
    private Component myHouseholdsTableCounterPartyTypeColumnGenerator(MyHousehold myHousehold) {
        Label retLabel = uiComponents.create(Label.class);

        if (dataManager.load(AgriculturalManufacturer.class)
                .query("select e from AgriculturalManufacturer e where e.counterpartyName = :companyName")
                .parameter("companyName", myHousehold.getCounterpartyName())
                .list().size() > 0) {
            retLabel.setValue(TradeRole.SELLER);
        } else {
            retLabel.setValue(TradeRole.BUYER);
        }

        return retLabel;
    }
}