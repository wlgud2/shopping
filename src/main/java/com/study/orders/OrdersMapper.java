package com.study.orders;
 
import java.util.List;
import java.util.Map;
 
public interface OrdersMapper {
 
        int create(OrdersDTO dto);
 
        int update(OrdersDTO dto);
 
        int total(Map map);
 
        List<OrdersDTO> list(Map map);
 
        int updateFile(Map map);

		OrdersDTO read(int orderno);
		
		List<Map> getCategory();
		
		List<OrdersDTO> mainlist(Map map);
		
		OrdersDTO detail(int orderno);

		int passcheck(String passwd);

		int delete(int orderno);
}
