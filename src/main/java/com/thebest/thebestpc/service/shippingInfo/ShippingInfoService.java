package com.thebest.thebestpc.service.shippingInfo;

import com.thebest.thebestpc.model.Orders;
import com.thebest.thebestpc.model.ShippingInfo;

public interface ShippingInfoService {
    void addShippingInfoToOrders(ShippingInfo shippingInfo, Orders orders);
}
