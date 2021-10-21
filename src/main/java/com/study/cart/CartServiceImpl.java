package com.study.cart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.cart.CartServiceImpl")
public class CartServiceImpl implements CartService {
	@Autowired
	private CartMapper mapper;

	@Override
	public int create(CartDTO dto) {
		// TODO Auto-generated method stub
		return mapper.create(dto);
	}

	@Override
	public int total(Map map) {
		// TODO Auto-generated method stub
		return mapper.total(map);
	}

	@Override
	public List<CartDTO> list(Map map) {
		// TODO Auto-generated method stub
		return mapper.list(map);
	}

	@Override
	public int delete(int orderno) {
		// TODO Auto-generated method stub
		return mapper.delete(orderno);
	}

	@Override
	public int passcheck(Map map) {
		// TODO Auto-generated method stub
		return mapper.passcheck(map);
	}
}
