package com.study.cart;
 
import java.util.List;
import java.util.Map;
 
public interface CartMapper {
 
        int create(CartDTO dto);
 
        int total(Map map);
 
        List<CartDTO> list(Map map);

		int passcheck(Map map);

		int delete(int orderno);
}
