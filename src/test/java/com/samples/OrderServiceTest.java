package com.samples;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mockito;
import java.util.List;

public class OrderServiceTest {


    private final static int CUSTOMER_ID = 1;

    @Test
    public void test_getOrderSummary_success(){

        // setup
        OrderService target = new OrderService();

        // preparing fixtures
        OrderEntity orderEntityFixture = new OrderEntity();
        List<OrderEntity> orderEntityListFixture = asList(orderEntityFixture);

        OrderSummary orderSummaryFixture = new OrderSummary();

        // inject first mock
        OrderDao mockOrderDao = mock(OrderDao.class);
        target.setOrderDao(mockOrderDao);

        // inject second mock
        OrderTransformer mockTransformer = mock(OrderTransformer.class);
        target.setTransformer(mockTransformer);

        // stub interaction with mocks ( fixtures are returned )
        when(mockOrderDao.findOrdersByCustomer(CUSTOMER_ID)).thenReturn(orderEntityListFixture);
        when(mockTransformer.transform(orderEntityFixture)).thenReturn(orderSummaryFixture);


        // execution
        List<OrderSummary> result = target.getOrderSummary(CUSTOMER_ID);

        // verification - results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(orderSummaryFixture, result.get(0));

        // verification - interactions
        verify(mockOrderDao).findOrdersByCustomer(CUSTOMER_ID);
        verify(mockTransformer).transform(orderEntityFixture);
    }

}
