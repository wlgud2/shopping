package com.study.orders;
 
import java.util.List;
import java.util.Map;
 
public interface OrdersMapper {
 
        int create(OrdersDTO dto);
        
        int total(Map map);
 
        List<OrdersDTO> list(Map map);
 
		OrdersDTO detail(int orderno);

}
