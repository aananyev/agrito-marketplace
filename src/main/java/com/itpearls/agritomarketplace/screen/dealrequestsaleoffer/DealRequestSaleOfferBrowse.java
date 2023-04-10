package com.itpearls.agritomarketplace.screen.dealrequestsaleoffer;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.DealRequestSaleOffer;

@UiController("DealRequestSaleOffer.browse")
@UiDescriptor("deal-request-sale-offer-browse.xml")
@LookupComponent("dealRequestSaleOffersTable")
public class DealRequestSaleOfferBrowse extends StandardLookup<DealRequestSaleOffer> {
}