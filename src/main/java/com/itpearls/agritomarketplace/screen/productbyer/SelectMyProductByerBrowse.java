package com.itpearls.agritomarketplace.screen.productbyer;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductByer;

@UiController("SelectMyProductByer.browse")
@UiDescriptor("select-my-product-byer-browse.xml")
@LookupComponent("productByersTable")
public class SelectMyProductByerBrowse extends StandardLookup<ProductByer> {
}