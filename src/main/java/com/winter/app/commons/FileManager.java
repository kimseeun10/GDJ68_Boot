package com.winter.app.commons;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	//file 저장 후 파일명 리턴
	public String save(String path, MultipartFile multipartFile)throws Exception{
		//어디에, 어떤 파일을?
		//1. 파일 객체 생성
		File file = new File(path);
		
		//폴더가 존재하는지 확인 후 없으면 만들어주기
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//2. 저장할 파일명 생성(중복되지 않는 이름)
		
		String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		//3. 파일을 저장
		file = new File(file, fileName);
		//FileCopyUtils 사용
		//FileCopyUtils.copy(multipartFile.getBytes(), file);
		//transferTo 사용
		multipartFile.transferTo(file);
		
		return fileName;
	}
	
}
