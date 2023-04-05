package com.itpearls.agritomarketplace.screen.variety;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Variety;

@UiController("Variety.edit")
@UiDescriptor("variety-edit.xml")
@EditedEntityContainer("varietyDc")
public class VarietyEdit extends StandardEditor<Variety> {
}