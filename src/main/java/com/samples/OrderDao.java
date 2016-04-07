package com.samples;

import java.util.List;

/**
 * Created by gabrielefalace on 07.04.16.
 */
public interface OrderDao {
    List<OrderEntity> findOrdersByCustomer(long customerId);
}
