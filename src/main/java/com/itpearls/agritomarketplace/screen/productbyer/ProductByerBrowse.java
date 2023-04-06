package com.itpearls.agritomarketplace.screen.productbyer;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductByer;

@UiController("ProductByer.browse")
@UiDescriptor("product-byer-browse.xml")
@LookupComponent("productByersTable")
public class ProductByerBrowse extends StandardLookup<ProductByer> {
}