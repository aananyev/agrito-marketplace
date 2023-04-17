package com.itpearls.agritomarketplace.screen.mytradeorganisation;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.MyTradeOrganisation;

@UiController("MyTradeOrganisation.browse")
@UiDescriptor("my-trade-organisation-browse.xml")
@LookupComponent("myTradeOrganisationsTable")
public class MyTradeOrganisationBrowse extends StandardLookup<MyTradeOrganisation> {
}