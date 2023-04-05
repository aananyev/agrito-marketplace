package com.itpearls.agritomarketplace.screen.product;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Product;

@UiController("Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
public class ProductBrowse extends StandardLookup<Product> {
}