package com.itpearls.agritomarketplace.screen.unitmeasurment;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.UnitMeasurment;

@UiController("UnitMeasurment.edit")
@UiDescriptor("unit-measurment-edit.xml")
@EditedEntityContainer("unitMeasurmentDc")
public class UnitMeasurmentEdit extends StandardEditor<UnitMeasurment> {
}