package com.itpearls.agritomarketplace.screen.position;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Position;

@UiController("Position_.edit")
@UiDescriptor("position-edit.xml")
@EditedEntityContainer("positionDc")
public class PositionEdit extends StandardEditor<Position> {
}