package com.itpearls.agritomarketplace.screen.producttype;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductType;

@UiController("ProductType.browse")
@UiDescriptor("product-type-browse.xml")
@LookupComponent("productTypesTable")
public class ProductTypeBrowse extends StandardLookup<ProductType> {
}