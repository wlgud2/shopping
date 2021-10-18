package com.study.member;

import java.util.List;
import java.util.Map;

public interface MemberMapper {

	int duplicatedId(String id);//생성되면 member.xml에서 중복확인하는 구문 추가

	int duplicatedEmail(String email);//member.xml에 추가

	int create(MemberDTO dto);//xml로 가기

	int loginCheck(Map<String, String> map);//xml
	
	String getGrade(String id);//xml

	MemberDTO read(String id);//xml

	int update(MemberDTO dto);

	int updateFile(Map map);

	List<MemberDTO> list(Map map);

	int total(Map map);
	
	MemberDTO mypage(String id); // join 하는 거

}
