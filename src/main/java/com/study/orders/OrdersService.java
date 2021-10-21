package com.study.orders;

import java.util.List;
import java.util.Map;

public interface OrdersService {//요청할 수 있는 인터페이스 선언돼있음(dao 형식)

	int create(OrdersDTO dto);

	int total(Map map);

	List<OrdersDTO> list(Map map);

	OrdersDTO detail(int orderno);
	
}
 
