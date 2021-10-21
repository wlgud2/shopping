package com.study.cart;
 
import java.util.List;
import java.util.Map;
 
public interface CartMapper {
 
        int create(CartDTO dto);
 
        int update(CartDTO dto);
 
        int total(Map map);
 
        List<CartDTO> list(Map map);
 
        int updateFile(Map map);

		CartDTO read(int orderno);
		
		List<Map> getCategory();
		
		List<CartDTO> mainlist(Map map);
		
		CartDTO detail(int orderno);

		int passcheck(String passwd);

		int delete(int orderno);
}
