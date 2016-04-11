package com.samples;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class OrderServiceImplTest {

    OrderTransformer transformer;

    OrderDao orderDao;

    OrderServiceImpl target;


    private final static int CUSTOMER_ID = 1;


    @Before
    public void setup(){
        orderDao = mock(OrderDao.class);
        transformer = mock(OrderTransformer.class);
        target = new OrderServiceImpl();
        target.setOrderDao(orderDao);
        target.setTransformer(transformer);
    }



    @Test
    public void test_getOrderSummary_success(){

        // setup
        OrderServiceImpl target = new OrderServiceImpl();

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


    @Test
    public void test_openNewOrder_successfullyRetriesDataInsert() throws Exception{
        when(orderDao.insert(any(OrderEntity.class)))
                .thenThrow(new RuntimeException("First Ex"))
                .thenReturn(1);


        target.openNewOrder(CUSTOMER_ID);
        verify(orderDao, times(2)).insert(any(OrderEntity.class));
    }


    @Test(expected = ServiceException.class)
    public void test_openNewOrder_failedDataInsert() throws Exception {
        when(orderDao.insert(any(OrderEntity.class)))
                .thenThrow(new RuntimeException("First Ex"))
                .thenThrow(new RuntimeException("Second Ex"));

         /*
           By using the try-finally we both check that
           exception is thrown && verify interactions.
         */
        try{
            target.openNewOrder(CUSTOMER_ID);
        }
        finally {
            verify(orderDao, times(2))
                    .insert(any(OrderEntity.class));
        }

    }

    @Test
    public void test_openNewOrder_success() throws Exception {
        when(orderDao.insert(any(OrderEntity.class)))
                .thenReturn(1);

        String orderNumber = target.openNewOrder(CUSTOMER_ID);

        ArgumentCaptor<OrderEntity> orderEntityCaptor = ArgumentCaptor.forClass(OrderEntity.class);

        verify(orderDao).insert(orderEntityCaptor.capture());

        OrderEntity capturedOrderEntity = orderEntityCaptor.getValue();

        assertNotNull(capturedOrderEntity);

        assertEquals(CUSTOMER_ID, capturedOrderEntity.getCustomerId());
        assertEquals(orderNumber, capturedOrderEntity.getOrderNumber());

    }

}
