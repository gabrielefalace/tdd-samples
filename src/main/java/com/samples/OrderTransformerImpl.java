package com.samples;

public class OrderTransformerImpl implements OrderTransformer {

    @Override
    public OrderSummary transform(OrderEntity order){
        OrderSummary summary = new OrderSummary();
        summary.setOrderNumber(order.getOrderNumber());
        return summary;
    }

}
