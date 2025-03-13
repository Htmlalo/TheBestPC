package com.thebest.thebestpc.service.manage;

import com.thebest.thebestpc.model.ShippingInfo;

public interface OrderManagerService {
    void addProductFromOrder(String userId, String cartId, ShippingInfo shippingInfo);

    void addShipInfoFromOrder();
}
