package com.knauer.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knauer.dscommerce.entities.OrderItem;
import com.knauer.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
