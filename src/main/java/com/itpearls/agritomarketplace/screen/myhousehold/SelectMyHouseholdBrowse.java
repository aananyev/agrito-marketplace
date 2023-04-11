package com.itpearls.agritomarketplace.screen.myhousehold;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.MyHousehold;

@UiController("SelectMyHousehold.browse")
@UiDescriptor("select-my-household-browse.xml")
@LookupComponent("myHouseholdsTable")
public class SelectMyHouseholdBrowse extends StandardLookup<MyHousehold> {
}