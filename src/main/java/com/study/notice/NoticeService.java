package com.study.notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {

	int create(NoticeDTO dto);

	int total(Map map);

	List<NoticeDTO> list(Map map);

	int upViewcnt(int noticeno);

	NoticeDTO read(int noticeno);

	int passcheck(Map map);

	int update(NoticeDTO dto);

	int delete(int noticeno);

}
