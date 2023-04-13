package com.itpearls.agritomarketplace.screen.bidding;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.Counterparty;
import com.itpearls.agritomarketplace.entity.TradeRole;
import com.itpearls.agritomarketplace.entity.TradingLot;
import io.jmix.core.EntityStates;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Bidding;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

@UiController("Bidding.edit")
@UiDescriptor("bidding-edit.xml")
@EditedEntityContainer("biddingDc")
public class BiddingEdit extends StandardEditor<Bidding> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private DateField<Date> dateProposalDateField;
    @Autowired
    private EntityPicker<Counterparty> couterpartyField;
    @Autowired
    private EntityPicker<TradingLot> tradingLotField;
    @Autowired
    private ComboBox<TradeRole> tradeRoleField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            dateProposalDateField.setValue(new Date());
            couterpartyField.setValue(AgritoGlobalValue.counterparty);
        }
    }

    @Subscribe("tradingLotField")
    public void onTradingLotFieldValueChange(HasValue.ValueChangeEvent<TradingLot> event) {
        if (tradingLotField.getValue().getAgriculturalManufacturer() != null) {
            if (tradingLotField.getValue().getAgriculturalManufacturer().equals(AgritoGlobalValue.counterparty)) {
                tradeRoleField.setValue(TradeRole.SELLER);
            } else {
                tradeRoleField.setValue(TradeRole.BUYER);
            }
        }
    }

    public void setProposalCostField(BigDecimal proposalCost) {

    }
}