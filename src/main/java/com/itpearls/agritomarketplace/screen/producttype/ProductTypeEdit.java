package com.itpearls.agritomarketplace.screen.producttype;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductType;

@UiController("ProductType.edit")
@UiDescriptor("product-type-edit.xml")
@EditedEntityContainer("productTypeDc")
public class ProductTypeEdit extends StandardEditor<ProductType> {
}