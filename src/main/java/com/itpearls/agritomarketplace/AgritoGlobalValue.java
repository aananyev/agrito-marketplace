package com.itpearls.agritomarketplace;

import com.itpearls.agritomarketplace.entity.*;

public class AgritoGlobalValue {
    public static MyHousehold myHousehold;
    public static MyTradeOrganisation myProductByer;
    public static Counterparty counterparty;
    public static TradeRole tradeRole;

    public MyHousehold getMyHousehold() {
        return myHousehold;
    }

    public void setMyHousehold(MyHousehold myHousehold) {
        this.myHousehold = myHousehold;
    }
}
