package com.study.member;

import java.util.List;
import java.util.Map;

public interface MemberService {

	int duplicatedId(String id);//빨간줄에 대고 add add unimpled하면 impl에 생성됨

	int duplicatedEmail(String email);//impl로 가기

	int create(MemberDTO dto);//impl로

	int loginCheck(Map<String, String> map);//impl
	
	String getGrade(String id);//impl

	MemberDTO read(String id);//impl

	int update(MemberDTO dto);

	int updateFile(Map map);

	List<MemberDTO> list(Map map);

	int total(Map map);

	MemberDTO mypage(String id); // join 하는 거

}
