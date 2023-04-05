package com.itpearls.agritomarketplace.screen.productgrade;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductGrade;

@UiController("ProductGrade.browse")
@UiDescriptor("product-grade-browse.xml")
@LookupComponent("productGradesTable")
public class ProductGradeBrowse extends StandardLookup<ProductGrade> {
}