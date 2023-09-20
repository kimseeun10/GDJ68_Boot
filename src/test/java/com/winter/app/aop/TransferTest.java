package com.winter.app.aop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransferTest {

	@Autowired
	private Transfer transfer;
	
	@Autowired
	private Card card;
	
	@Test
	void test() throws Exception {		
		transfer.useBus("123"); // 버스타는 로직 실행		

		transfer.useSubway("456"); // 지하철타는 로직 실행
	}

}
