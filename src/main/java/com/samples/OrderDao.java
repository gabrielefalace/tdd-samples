package com.samples;

import java.util.List;

public interface OrderDao {
    List<OrderEntity> findOrdersByCustomer(long customerId);
    int insert(OrderEntity orderEntity) throws Exception;
}
