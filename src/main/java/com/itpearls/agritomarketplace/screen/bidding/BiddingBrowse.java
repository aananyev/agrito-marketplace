package com.itpearls.agritomarketplace.screen.bidding;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Bidding;

@UiController("Bidding.browse")
@UiDescriptor("bidding-browse.xml")
@LookupComponent("biddingsTable")
public class BiddingBrowse extends StandardLookup<Bidding> {
}