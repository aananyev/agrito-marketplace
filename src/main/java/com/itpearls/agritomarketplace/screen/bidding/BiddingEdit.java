package com.itpearls.agritomarketplace.screen.bidding;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.Counterparty;
import com.itpearls.agritomarketplace.entity.TradeRole;
import com.itpearls.agritomarketplace.entity.TradingLot;
import io.jmix.core.EntityStates;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.builder.LookupBuilder;
import io.jmix.ui.component.*;
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
    @Autowired
    private Label previonsProposalCostTitleLabel;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private Label previonsProposalCostLabel;
    @Autowired
    private ScreenBuilders screenBuilders;

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

        if (getEditedEntity().getParentBidding() == null) {
            previonsProposalCostTitleLabel.setVisible(false);
            previonsProposalCostLabel.setVisible(false);
        } else {
            previonsProposalCostTitleLabel.setVisible(true);
            previonsProposalCostLabel.setVisible(true);

            previonsProposalCostTitleLabel.setValue(messageBundle.getMessage("msgParentBidding"));
            previonsProposalCostLabel.setValue(getEditedEntity().getParentBidding().getProposalCost());
        }
    }

    public void setProposalCostField(BigDecimal proposalCost) {

    }

    @Subscribe("showCostProposalHistory")
    public void onShowCostProposalHistoryClick(Button.ClickEvent event) {
        BiddingShowProposalCostsBrowse biddingHistory = screenBuilders.screen(this)
                .withScreenClass(BiddingShowProposalCostsBrowse.class)
                .withOpenMode(OpenMode.DIALOG)
                .build();
        biddingHistory.setTradingLot(getEditedEntity().getTradingLot());
        biddingHistory.show();
    }


}