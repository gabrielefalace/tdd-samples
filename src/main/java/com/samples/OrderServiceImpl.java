package com.samples;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl {

    private static final int MAX_INSERT_ATTEMPT = 2;
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

    public String openNewOrder(long customerId) throws ServiceException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerId(customerId);
        orderEntity.setOrderNumber(UUID.randomUUID().toString());

        boolean insertSuccessful = false;
        int insertAttempt = 1;

        while(!insertSuccessful && insertAttempt <= MAX_INSERT_ATTEMPT) {
            try {
                int resultValue = orderDao.insert(orderEntity);
                if (resultValue == 1)
                    insertSuccessful = true;
                else
                    ++insertAttempt;
            } catch (Exception e){
                ++insertAttempt;
            }
        }
        if (!insertSuccessful)
            throw new ServiceException("Data access error!");

        return orderEntity.getOrderNumber();
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
