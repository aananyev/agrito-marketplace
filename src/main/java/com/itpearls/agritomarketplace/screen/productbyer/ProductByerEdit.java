package com.itpearls.agritomarketplace.screen.productbyer;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductByer;

@UiController("ProductByer.edit")
@UiDescriptor("product-byer-edit.xml")
@EditedEntityContainer("productByerDc")
public class ProductByerEdit extends StandardEditor<ProductByer> {
}