package com.itpearls.agritomarketplace.screen.agriculturalmanufacturer;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.AgriculturalManufacturer;

@UiController("AgriculturalManufacturer.browse")
@UiDescriptor("agricultural-manufacturer-browse.xml")
@LookupComponent("agriculturalManufacturersTable")
public class AgriculturalManufacturerBrowse extends StandardLookup<AgriculturalManufacturer> {
}