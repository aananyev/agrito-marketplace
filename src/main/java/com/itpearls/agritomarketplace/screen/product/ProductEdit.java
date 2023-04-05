package com.itpearls.agritomarketplace.screen.product;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Product;

@UiController("Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
public class ProductEdit extends StandardEditor<Product> {
}