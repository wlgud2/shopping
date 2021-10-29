package com.study.cart;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.study.orders.OrdersDTO;

public class JoinDTO {
	private String id;
	private String passwd;
	private String mname;
	private String tel;
	private String email;
	private String zipcode;
	private String address1;
	private String address2;
	private String job;
	private String mdate;
	private String fname;
	private MultipartFile fnameMF;
	private String grade;
	private List<OrdersDTO> list;
	private int orderno;
	private int cartno;
	private int contentsno;
	private String odate;
	private int quantity;
	private int total;
	private String payment;
	private String pname;
}
