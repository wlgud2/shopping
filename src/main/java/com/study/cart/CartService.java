package com.study.cart;

import java.util.List;
import java.util.Map;

public interface CartService {//요청할 수 있는 인터페이스 선언돼있음(dao 형식)

	int create(CartDTO dto);

	int update(CartDTO dto);

	int total(Map map);

	List<CartDTO> list(Map map);

	int updateFile(Map map);

	CartDTO read(int orderno);
	
	List<Map> getCategory();
	
	List<CartDTO> mainlist(Map map);
	
	CartDTO detail(int orderno);

	int delete(int orderno);

	int passcheck(String passwd);
}
 
