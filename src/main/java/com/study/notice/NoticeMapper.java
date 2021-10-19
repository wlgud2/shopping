package com.study.notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {

	int create(NoticeDTO dto);
	
	int total(Map map);

	List<NoticeDTO> list(Map map);

	NoticeDTO read(int noticeno);

	int passcheck(Map map);
	
	int update(NoticeDTO dto);
	
	int delete(int noticeno);

	int upViewcnt(int noticeno);

}
