package com.winter.app.commons;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {

	//시작 인덱스 번호
	private Long startRow;
	
	//가져올 갯수
	private Long lastRow;
	
	//검색할 컬럼
	private String kind;
	
	//검색어
	private String search;
}
