package com.itpearls.agritomarketplace.screen.counterparty;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Counterparty;

@UiController("Counterparty.edit")
@UiDescriptor("counterparty-edit.xml")
@EditedEntityContainer("counterpartyDc")
public class CounterpartyEdit extends StandardEditor<Counterparty> {
}