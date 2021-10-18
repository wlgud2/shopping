package com.study.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.member.MemberServiceImpl")//""라는 이름으로 객체생성담당
public class MemberServiceImpl implements MemberService {
	@Autowired//service는 mapper를 참조해야한다
	private MemberMapper mapper;//이미 databasecontroller에서 mapper스캔을 통해 구현해놔서 autowired만 해주면 알아서 접근함

	@Override
	public int duplicatedId(String id) {
		// TODO Auto-generated method stub
		return mapper.duplicatedId(id);//빨간줄로 create하면 mapper에 duplicatedid 생성됨
	}

	@Override
	public int duplicatedEmail(String email) {
		// TODO Auto-generated method stub
		return mapper.duplicatedEmail(email);//mapper에 생성
	}

	@Override
	public int create(MemberDTO dto) {
		// TODO Auto-generated method stub
		return mapper.create(dto);//mapper로 가기
	}

	@Override
	public int loginCheck(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.loginCheck(map);//mapper에
	}

	@Override
	public String getGrade(String id) {
		// TODO Auto-generated method stub
		return mapper.getGrade(id);//mapper에
	}

	@Override
	public MemberDTO read(String id) {
		// TODO Auto-generated method stub
		return mapper.read(id);//mapper
	}

	@Override
	public int update(MemberDTO dto) {
		// TODO Auto-generated method stub
		return mapper.update(dto);
	}

	@Override
	public int updateFile(Map map) {
		// TODO Auto-generated method stub
		return mapper.updateFile(map);
	}

	@Override
	public int total(Map map) {
		// TODO Auto-generated method stub
		return mapper.total(map);
	}

	@Override
	public List<MemberDTO> list(Map map) {
		// TODO Auto-generated method stub
		return mapper.list(map);
	}

	@Override
	public MemberDTO mypage(String id) {
		// TODO Auto-generated method stub
		return mapper.mypage(id);
	}
}
