package com.samples;


import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public List<OrderEntity> findOrdersByCustomer(long customerId){
        return null;
    }

    @Override
    public int insert(OrderEntity orderEntity) {
        return 0;
    }

}
