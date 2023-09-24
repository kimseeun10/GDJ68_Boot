package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class NoticeService implements BoardService{

	@Autowired
	private NoticeDAO noticeDAO;

	@Autowired
	private FileManager fileManager;
	
	// properties 값을 java에서 사용하고 싶을 때 
	// @Value("${properties의 키}")
	@Value("${app.upload}")
	private String uploadPath;
	
	@Value("${app.board.notice}")
	private String boardName;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
//		pager.makeRowNum();
//		pager.makePageNum(noticeDAO.getCount(pager));
		return noticeDAO.getList(pager);
	}

	@Override
//	@Transactional(rollbackFor = Exception.class) //예외가 발생하면 rollback
	public int add(BoardVO boardVO,MultipartFile[]files) throws Exception {
		log.info("BoardNo : {}", boardVO.getBoardNo()); // add insert 하기 전
		int result = noticeDAO.add(boardVO);
		
		log.info("=============================");
		log.info("BoardNo : {}", boardVO.getBoardNo()); // add insert 한 후
		
		for(MultipartFile multipartFile:files) {
			
			if(multipartFile.isEmpty()) {
				continue;
			}
			
			NoticeFileVO fileVO = new NoticeFileVO();
			String fileName = fileManager.save(this.uploadPath+this.boardName, multipartFile);
			fileVO.setBoardNo(boardVO.getBoardNo());
			fileVO.setFileName(fileName);
			fileVO.setOriName(multipartFile.getOriginalFilename());
			result = noticeDAO.fileAdd(fileVO);
		}
		
		return result; //noticeDAO.add(boardVO);
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getDetail(boardVO);
	}

	
	@Override
	public FileVO getFileDetail(FileVO fileVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getFileDetail(fileVO);
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.setDelete(boardVO);
	}

	@Override
	public int setHitUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.setHitUpdate(boardVO);
	}

	
}
