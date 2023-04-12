package com.itpearls.agritomarketplace.screen.dealrequestpurchasebuy;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.*;
import io.jmix.core.EntityStates;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.component.ValidationException;
import io.jmix.ui.screen.*;
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
    private EntityPicker<Counterparty> productSellerField;
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private EntityPicker<Counterparty> productBuyerField;
    @Autowired
    private MessageBundle messageBundle;

    @Install(to = "amountField", subject = "validator")
    private void amountFieldValidator(BigDecimal value) {
        if (amountField.getValue().compareTo(lotForSellField.getValue().getProductAmount()) > 0) {
            throw new ValidationException(messageBundle.getMessage("msgWrongAmount")
                    + " "
                    + lotForSellField.getValue().getProductAmount()
                    + " "
                    + lotForSellField.getValue().getUnitMeasurment().getNameUnit()
                    + " "
                    + lotForSellField.getValue().getProduct().getProductName());
        }
    }

    public void setLotForSell(LotForSell lotForSell) {
        lotForSellField.setValue(lotForSell);

        amountField.setValue(lotForSell.getProductAmount());
        proposalCostField.setValue(lotForSell.getPrice());
    }

    @Subscribe("lotForSellField")
    public void onLotForSellFieldValueChange(HasValue.ValueChangeEvent<LotForSell> event) {
        productSellerField.setValue((AgriculturalManufacturer) event.getValue().getAgriculturalManufacturer());
        amountField.setValue(event.getValue().getProductAmount());
        proposalCostField.setValue(event.getValue().getPrice());
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            productBuyerField.setValue(AgritoGlobalValue.counterparty);
        }
    }
}