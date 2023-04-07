package com.itpearls.agritomarketplace.screen.typeactivity;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.TypeActivity;

@UiController("TypeActivity.browse")
@UiDescriptor("type-activity-browse.xml")
@LookupComponent("typeActivitiesTable")
public class TypeActivityBrowse extends StandardLookup<TypeActivity> {
}