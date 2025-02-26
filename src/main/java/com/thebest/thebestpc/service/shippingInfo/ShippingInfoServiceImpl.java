package com.thebest.thebestpc.service.shippingInfo;

import com.thebest.thebestpc.model.Orders;
import com.thebest.thebestpc.model.ShippingInfo;
import com.thebest.thebestpc.repository.ShippingInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class ShippingInfoServiceImpl implements ShippingInfoService {


    private final ShippingInfoRepository shippingInfoRepository;

    public ShippingInfoServiceImpl(ShippingInfoRepository shippingInfoRepository) {
        this.shippingInfoRepository = shippingInfoRepository;
    }

    @Override
    public void addShippingInfoToOrders(ShippingInfo shippingInfo, Orders orders) {
        shippingInfo.setOrders(orders);

        shippingInfoRepository.save(shippingInfo);
    }
}
