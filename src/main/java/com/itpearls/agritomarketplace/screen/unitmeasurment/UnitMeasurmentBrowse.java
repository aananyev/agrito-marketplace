package com.itpearls.agritomarketplace.screen.unitmeasurment;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.UnitMeasurment;

@UiController("UnitMeasurment.browse")
@UiDescriptor("unit-measurment-browse.xml")
@LookupComponent("unitMeasurmentsTable")
public class UnitMeasurmentBrowse extends StandardLookup<UnitMeasurment> {
}