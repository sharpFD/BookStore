package com.wyg.mapper;

import java.util.List;

import com.wyg.pojo.Order;

public interface OrderMapper {
	void insertOrder(Order order);
	List<Order> findOrderByUserId(long id);
	int getCurrentiId();
	void updateOrderById(Order order);
	Order findOrderByOrderId(long id);
}
