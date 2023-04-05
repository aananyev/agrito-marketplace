package com.itpearls.agritomarketplace.screen.productgrade;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductGrade;

@UiController("ProductGrade.edit")
@UiDescriptor("product-grade-edit.xml")
@EditedEntityContainer("productGradeDc")
public class ProductGradeEdit extends StandardEditor<ProductGrade> {
}