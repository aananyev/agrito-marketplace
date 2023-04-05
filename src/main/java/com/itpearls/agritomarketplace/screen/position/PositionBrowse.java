package com.itpearls.agritomarketplace.screen.position;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Position;

@UiController("Position_.browse")
@UiDescriptor("position-browse.xml")
@LookupComponent("positionsTable")
public class PositionBrowse extends StandardLookup<Position> {
}