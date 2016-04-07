package com.samples;

import static java.util.Arrays.*;

import static org.junit.Assert.*;
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
        OrderDao mockOrderDao = Mockito.mock(OrderDao.class);
        target.setOrderDao(mockOrderDao);

        // inject second mock
        OrderTransformer mockTransformer = Mockito.mock(OrderTransformer.class);
        target.setTransformer(mockTransformer);

        // stub interaction with mocks ( fixtures are returned )
        Mockito.when(mockOrderDao.findOrdersByCustomer(CUSTOMER_ID)).thenReturn(orderEntityListFixture);
        Mockito.when(mockTransformer.transform(orderEntityFixture)).thenReturn(orderSummaryFixture);


        // execution
        List<OrderSummary> result = target.getOrderSummary(CUSTOMER_ID);

        // verification - results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(orderSummaryFixture, result.get(0));

        // verification - interactions
        Mockito.verify(mockOrderDao).findOrdersByCustomer(CUSTOMER_ID);
        Mockito.verify(mockTransformer).transform(orderEntityFixture);


    }






}
