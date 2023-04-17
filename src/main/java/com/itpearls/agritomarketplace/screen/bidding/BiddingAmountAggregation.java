package com.itpearls.agritomarketplace.screen.bidding;

import com.itpearls.agritomarketplace.entity.TradingLot;
import io.jmix.ui.component.data.aggregation.AggregationStrategy;
import liquibase.repackaged.org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collection;

public class BiddingAmountAggregation implements AggregationStrategy<TradingLot, String> {
    @Override
    public String aggregate(Collection<TradingLot> propertyValues) {
        BigDecimal sumApproved = BigDecimal.ZERO;

        if (CollectionUtils.isNotEmpty(propertyValues)) {
            for (TradingLot tradingLot : propertyValues) {
//                if (tradingLot.)
//                    sumApproved.add(bidding);
            }
        }

        String ret = sumApproved.toString();

        return String.format("%s", ret);
    }

    @Override
    public Class<String> getResultClass() {
        return String.class;
    }
}
