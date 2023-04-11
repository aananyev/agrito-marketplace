package com.itpearls.agritomarketplace;

import com.itpearls.agritomarketplace.entity.AgriculturalManufacturer;
import com.itpearls.agritomarketplace.entity.MyHousehold;
import com.itpearls.agritomarketplace.entity.ProductByer;

public class AgritoGlobalValue {
    public static MyHousehold myHousehold;
    public static AgriculturalManufacturer agriculturalManufacturer;
    public static ProductByer productByer;

    public AgriculturalManufacturer getAgriculturalManufacturer() {
        return agriculturalManufacturer;
    }

    public MyHousehold getMyHousehold() {
        return myHousehold;
    }

    public ProductByer getProductByer() {
        return productByer;
    }

    public void setAgriculturalManufacturer(AgriculturalManufacturer agriculturalManufacturer) {
        this.agriculturalManufacturer = agriculturalManufacturer;
    }

    public void setMyHousehold(MyHousehold myHousehold) {
        this.myHousehold = myHousehold;
    }

    public void setProductByer(ProductByer productByer) {
        this.productByer = productByer;
    }
}
