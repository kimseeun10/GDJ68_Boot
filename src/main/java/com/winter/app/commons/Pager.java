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
	
	private Long page;
	// (count 역할) 한 페이지에 보여질 data(row)의 갯수
	private Long perPage; 
	
	// 총 페이지의 갯수
	private Long totalPage;
	
	// 시작번호
	private Long startNum;
	
	//끝번호
	private Long lastNum;
	
	//이전 블럭 활성화를 담는 변수
	private boolean pre; //false면 1번 블럭, true면 1번 블럭 아님
	
	//다음 블럭 활성화를 담는 변수
	private boolean next; //false면 마지막 블럭, true면 마지막 블럭 아님
	
	public void makePageNum(Long total) {
		//130 -> 13
		//131~139 -> 14
		
		//1. 전체 갯수로 전체 페이지 수 구하기
		this.totalPage = total/this.getPerPage();
		if(total%this.getPerPage()!=0) {
			this.totalPage++; 
			}
		//2. 전체 페이지 수로 전체 block 수 구하기
		//한 페이지에 출력 할 번호의 갯수 의미
		long perBlock=5;
		
		long totalBlock = this.totalPage/perBlock;
		if(this.totalPage%perBlock != 0) {
			totalBlock++;
		}
		
		//3. 현재 페이지 번호로 block번호 구하기 
		//현재 블럭 번호
		long curBlock = this.getPage()/perBlock;
		
		if(this.getPage()%perBlock != 0) {
			curBlock++;
		}
		
		//4. 현재 block번호의 시작번호와 끝번호 구하기
		//curBlock 		startNum	  lastNum
		//   1				1			 5
		//	 2 				6			 10
		//	 3				11			 15
		
		this.startNum= (curBlock-1)*perBlock+1;
		this.lastNum= curBlock*perBlock;
		
		//이전 블럭 활성화 여부
		if(curBlock>1) {
			this.pre=true;
		}
		
		//다음 블럭 확성화 여부
		if(curBlock<totalBlock) {
			this.next=true;
		}
		
		//현재 블럭이 마지막 블럭일 때 lastNum을 totalPage숫자로 대입
		if(!this.next) {
			this.lastNum=totalPage;
		}
		
	}
	
	public void makeRowNum() {
		this.startRow=(this.getPage()-1)*this.getPerPage()+1;
		this.lastRow=this.getPage()*this.getPerPage();
	}
	
	public Long getStartRow() {
		if(this.startRow==null || this.startRow<0) {
			return 0L;
		}
		return this.startRow;
	}
	
	public Long getLastRow() {
		if(this.lastRow==null || this.lastRow<0) {
			return 10L;
		}
		return this.lastRow;
	}
	
	public String getSearch() {
		if(this.search==null) {
			return "";
		}
		return this.search;
	}
}
