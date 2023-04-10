package com.itpearls.agritomarketplace.screen.dealrequestpurchasebuy;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.DealRequestPurchaseBuy;

@UiController("DealRequestPurchaseBuy.browse")
@UiDescriptor("deal-request-purchase-buy-browse.xml")
@LookupComponent("dealRequestPurchaseBuysTable")
public class DealRequestPurchaseBuyBrowse extends StandardLookup<DealRequestPurchaseBuy> {
}