package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.board.notice.NoticeFileVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class QnaService implements BoardService{

	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String uploadPath;
	
	@Value("${app.board.qna}")
	private String boardName;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getList(pager);
	}

	@Override
	public int add(BoardVO boardVO, MultipartFile[] files) throws Exception {
		// TODO Auto-generated method stub
		int result = qnaDAO.add(boardVO);
		
		for(MultipartFile multipartFile:files) {
			
			if(multipartFile.isEmpty()) {
				continue;
			}
			
			NoticeFileVO fileVO = new NoticeFileVO();
			String fileName = fileManager.save(this.uploadPath+this.boardName, multipartFile);
			fileVO.setBoardNo(boardVO.getBoardNo());
			fileVO.setFileName(fileName);
			fileVO.setOriName(multipartFile.getOriginalFilename());
			result = qnaDAO.fileAdd(fileVO);
		}
		
		return result;
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getDetail(boardVO);
	}

	@Override
	public FileVO getFileDetail(FileVO fileVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getFileDetail(fileVO);
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setHitUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
