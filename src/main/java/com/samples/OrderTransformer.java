package com.samples;

/**
 * Created by gabrielefalace on 07.04.16.
 */
public interface OrderTransformer {
    OrderSummary transform(OrderEntity order);
}
