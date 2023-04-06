package com.itpearls.agritomarketplace.screen.counterpartyrating;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.CounterpartyRating;

@UiController("CounterpartyRating.browse")
@UiDescriptor("counterparty-rating-browse.xml")
@LookupComponent("counterpartyRatingsTable")
public class CounterpartyRatingBrowse extends StandardLookup<CounterpartyRating> {
}