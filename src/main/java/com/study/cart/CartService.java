package com.study.cart;

import java.util.List;
import java.util.Map;

public interface CartService {//요청할 수 있는 인터페이스 선언돼있음(dao 형식)

	int create(CartDTO dto);

	int total(Map map);

	List<CartDTO> list(Map map);

	int delete(int orderno);

	int passcheck(Map map);
}
 
