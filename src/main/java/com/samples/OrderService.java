package com.samples;

import java.util.LinkedList;
import java.util.List;

public class OrderService {

    private OrderDao orderDao;

    private OrderTransformer transformer;

    public List<OrderSummary> getOrderSummary(long customerId){
        List<OrderSummary> resultList = new LinkedList<>();

        List<OrderEntity> orderEntityList = orderDao.findOrdersByCustomer(customerId);

        for(OrderEntity currentEntity : orderEntityList) {
            OrderSummary summary = transformer.transform(currentEntity);
            resultList.add(summary);
        }
        return resultList;
    }


    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderTransformer getTransformer() {
        return transformer;
    }

    public void setTransformer(OrderTransformer transformer) {
        this.transformer = transformer;
    }
}
