package com.itpearls.agritomarketplace.screen.tradeorganisationtype;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.TradeOrganisationType;

@UiController("TradeOrganisationType.browse")
@UiDescriptor("trade-organisation-type-browse.xml")
@LookupComponent("tradeOrganisationTypesTable")
public class TradeOrganisationTypeBrowse extends StandardLookup<TradeOrganisationType> {
}