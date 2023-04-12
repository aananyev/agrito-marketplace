package com.itpearls.agritomarketplace;

import com.itpearls.agritomarketplace.entity.Counterparty;
import com.itpearls.agritomarketplace.entity.MyHousehold;

public class AgritoGlobalValue {
    public static MyHousehold myHousehold;
    public static Counterparty counterparty;

    public MyHousehold getMyHousehold() {
        return myHousehold;
    }

    public void setMyHousehold(MyHousehold myHousehold) {
        this.myHousehold = myHousehold;
    }
}
