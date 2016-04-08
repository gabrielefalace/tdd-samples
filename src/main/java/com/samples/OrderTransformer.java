package com.samples;

public interface OrderTransformer {
    OrderSummary transform(OrderEntity order);
}
