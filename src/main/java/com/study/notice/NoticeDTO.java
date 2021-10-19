package com.study.notice;

import lombok.Data;

@Data
public class NoticeDTO {
	private int noticeno;
	private String title;
	private String content;
	private String wname;
	private String passwd;
	private int viewcnt;
	private String ndate;
}
