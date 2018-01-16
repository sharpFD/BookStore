package com.wyg.mapper;

import java.util.Set;

import com.wyg.pojo.OrderLine;

public interface OrderLineMapper {
	void insertOrderLine(OrderLine orderLine);
	Set<OrderLine> findOrderLineByOrderId(long id);
}
