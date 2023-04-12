package com.itpearls.agritomarketplace.screen.bidding;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Bidding;

@UiController("Bidding.edit")
@UiDescriptor("bidding-edit.xml")
@EditedEntityContainer("biddingDc")
public class BiddingEdit extends StandardEditor<Bidding> {
}