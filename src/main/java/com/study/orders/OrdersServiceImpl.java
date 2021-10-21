package com.study.orders;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.order.OrderServiceImpl")
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper mapper;

	@Override
	public int create(OrdersDTO dto) {
		// TODO Auto-generated method stub
		return mapper.create(dto);
	}

	@Override
	public int update(OrdersDTO dto) {
		// TODO Auto-generated method stub
		return mapper.update(dto);
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
	public int updateFile(Map map) {
		// TODO Auto-generated method stub
		return mapper.updateFile(map);
	}

	@Override
	public OrdersDTO read(int orderno) {
		// TODO Auto-generated method stubS
		return mapper.read(orderno);
	}

	@Override
	public List<Map> getCategory() {
		// TODO Auto-generated method stub
		return mapper.getCategory();
	}

	@Override
	public List<OrdersDTO> mainlist(Map map) {
		// TODO Auto-generated method stub
		return mapper.mainlist(map);
	}

	@Override
	public OrdersDTO detail(int orderno) {
		// TODO Auto-generated method stub
		return mapper.detail(orderno);
	}

	@Override
	public int delete(int orderno) {
		// TODO Auto-generated method stub
		return mapper.delete(orderno);
	}

	@Override
	public int passcheck(String passwd) {
		// TODO Auto-generated method stub
		return mapper.passcheck(passwd);
	}
}
