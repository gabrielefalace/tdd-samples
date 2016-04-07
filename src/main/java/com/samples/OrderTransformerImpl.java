package com.samples;


public class OrderTransformerImpl {

    public OrderSummary transform(OrderEntity order){
        OrderSummary summary = new OrderSummary();
        summary.setOrderNumber(order.getOrderNumber());
        return summary;
    }

}
