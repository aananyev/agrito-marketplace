package com.itpearls.agritomarketplace;

import com.itpearls.agritomarketplace.entity.Counterparty;
import com.itpearls.agritomarketplace.entity.MyHousehold;
import com.itpearls.agritomarketplace.entity.ProductByer;
import com.itpearls.agritomarketplace.entity.TradeRole;

public class AgritoGlobalValue {
    public static MyHousehold myHousehold;
    public static ProductByer myProductByer;
    public static Counterparty counterparty;
    public static TradeRole tradeRole;

    public MyHousehold getMyHousehold() {
        return myHousehold;
    }

    public void setMyHousehold(MyHousehold myHousehold) {
        this.myHousehold = myHousehold;
    }
}
