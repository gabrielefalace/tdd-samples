package com.samples;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.UUID;

public class OrderEntityToSummaryTransformation {

    OrderTransformerImpl transformer = new OrderTransformerImpl();

    @Test
    public void test_transform_success() {

        String orderNumberFixture = UUID.randomUUID().toString();
        OrderEntity entity = new OrderEntity();
        entity.setOrderNumber(orderNumberFixture);

        OrderSummary result = transformer.transform(entity);

        assertNotNull(result);

    }

}
