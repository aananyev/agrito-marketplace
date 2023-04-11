package com.itpearls.agritomarketplace.listener;

import com.itpearls.agritomarketplace.entity.DealRequest;
import com.itpearls.agritomarketplace.entity.DealRequestStatus;
import io.jmix.core.EntityStates;
import io.jmix.core.event.EntityLoadingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DealRequestEventListener {

    @Autowired
    private EntityStates entityStates;

    @EventListener
    public void onDealRequestLoading(EntityLoadingEvent<DealRequest> event) {
        if(entityStates.isNew(event.getEntity())) {
            event.getEntity().setDealRequestStatus(DealRequestStatus.NEW);
        }
    }
}