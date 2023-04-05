package com.itpearls.agritomarketplace.screen.counterparty;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Counterparty;

@UiController("Counterparty.browse")
@UiDescriptor("counterparty-browse.xml")
@LookupComponent("counterpartiesTable")
public class CounterpartyBrowse extends StandardLookup<Counterparty> {
}