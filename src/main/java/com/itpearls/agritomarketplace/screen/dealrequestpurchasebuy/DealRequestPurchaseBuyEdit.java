package com.itpearls.agritomarketplace.screen.dealrequestpurchasebuy;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.AgriculturalManufacturer;
import com.itpearls.agritomarketplace.entity.LotForSell;
import com.itpearls.agritomarketplace.entity.ProductByer;
import io.jmix.core.EntityStates;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.DealRequestPurchaseBuy;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@UiController("DealRequestPurchaseBuy.edit")
@UiDescriptor("deal-request-purchase-buy-edit.xml")
@EditedEntityContainer("dealRequestPurchaseBuyDc")
public class DealRequestPurchaseBuyEdit extends StandardEditor<DealRequestPurchaseBuy> {
    @Autowired
    private EntityPicker<LotForSell> lotForSellField;
    @Autowired
    private TextField<BigDecimal> amountField;
    @Autowired
    private TextField<BigDecimal> proposalCostField;
    @Autowired
    private EntityPicker<AgriculturalManufacturer> productSellerField;
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private EntityPicker<ProductByer> productBuyerField;

    public void setLotForSell(LotForSell lotForSell) {
        lotForSellField.setValue(lotForSell);

        amountField.setValue(lotForSell.getProductAmount());
        proposalCostField.setValue(lotForSell.getPrice());
    }

    @Subscribe("lotForSellField")
    public void onLotForSellFieldValueChange(HasValue.ValueChangeEvent<LotForSell> event) {
        productSellerField.setValue(event.getValue().getAgriculturalManufacturer());
        amountField.setValue(event.getValue().getProductAmount());
        proposalCostField.setValue(event.getValue().getPrice());
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            productBuyerField.setValue(AgritoGlobalValue.productByer);
        }
    }
}