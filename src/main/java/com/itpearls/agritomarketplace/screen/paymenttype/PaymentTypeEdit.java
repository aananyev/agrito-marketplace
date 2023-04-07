package com.itpearls.agritomarketplace.screen.paymenttype;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.PaymentType;

@UiController("PaymentType.edit")
@UiDescriptor("payment-type-edit.xml")
@EditedEntityContainer("paymentTypeDc")
public class PaymentTypeEdit extends StandardEditor<PaymentType> {
}