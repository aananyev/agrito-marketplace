package com.itpearls.agritomarketplace.screen.paymenttype;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.PaymentType;

@UiController("PaymentType.browse")
@UiDescriptor("payment-type-browse.xml")
@LookupComponent("paymentTypesTable")
public class PaymentTypeBrowse extends StandardLookup<PaymentType> {
}