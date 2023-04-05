package com.itpearls.agritomarketplace.screen.variety;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Variety;

@UiController("Variety.browse")
@UiDescriptor("variety-browse.xml")
@LookupComponent("varietiesTable")
public class VarietyBrowse extends StandardLookup<Variety> {
}