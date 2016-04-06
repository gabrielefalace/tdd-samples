package com.samples;

import static org.junit.Assert.*;
import org.junit.Test;

public class OrderEntityToSummaryTransformation {

    OrderTransformer transformer = new OrderTransformer();

    @Test
    public void test_transform_success() {

        OrderSummary result = transformer.transform(null);

        assertNotNull(result);

    }

}
