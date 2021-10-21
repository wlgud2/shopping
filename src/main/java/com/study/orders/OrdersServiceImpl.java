package com.study.orders;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.orders.OrdersServiceImpl")
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper mapper;

	@Override
	public int create(OrdersDTO dto) {
		// TODO Auto-generated method stub
		return mapper.create(dto);
	}

	@Override
	public int total(Map map) {
		// TODO Auto-generated method stub
		return mapper.total(map);
	}

	@Override
	public List<OrdersDTO> list(Map map) {
		// TODO Auto-generated method stub
		return mapper.list(map);
	}

	@Override
	public OrdersDTO detail(int orderno) {
		// TODO Auto-generated method stub
		return mapper.detail(orderno);
	}

}
