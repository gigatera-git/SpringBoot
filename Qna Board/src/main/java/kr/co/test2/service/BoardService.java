package kr.co.test2.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.test2.mapper.BoardMapper;
import kr.co.test2.vo.BoardPagingVO;
import kr.co.test2.vo.BoardSearchVO;
import kr.co.test2.vo.BoardVO;



@Service
public class BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	

	// list
	public Map<String, Integer> getPreList(BoardSearchVO boardSearchVO, BoardPagingVO boardPagingVO) {
		return  boardMapper.getPreList(boardSearchVO, boardPagingVO);
	}
	
	public List<BoardVO> getList(BoardSearchVO boardSearchVO, BoardPagingVO boardPagingVO) {
		return  boardMapper.getList(boardSearchVO, boardPagingVO);
	}
	
	//view
	public Integer setClick(Integer idx) {
		return boardMapper.setClick(idx);
	}
	public Map<String,String> getView(Integer idx) {
		return boardMapper.getView(idx);
	}
	
	//mod
	public Map<String,String> getMod(Integer idx) {
		return boardMapper.getMod(idx);
	}
	
	//mod pwd chk
	public Integer getPwdChk(BoardVO boardVO) {
		return boardMapper.getPwdChk(boardVO);
	}
	
	//mod ok
	public Integer getModOk(BoardVO boardVO) {
		return boardMapper.getModOk(boardVO);
	}
	
	//dell
	public Integer delOk(Integer idx) {
		return boardMapper.delOk(idx);
	}
	
	// write
	public Integer getRef() {
		return boardMapper.getRef();
	}
	public Integer writeOk(BoardVO boardVO) {
		return boardMapper.writeOk(boardVO);
	}
	
	//reply
	public Map<String,Integer> getReGrp(Integer idx) {
		return boardMapper.getReGrp(idx);
	}
	public Integer replyPre(Map<String,Integer> regrp) {
		return boardMapper.replyPre(regrp);
	}
	public Integer replyOk(BoardVO boardVO) {
		return boardMapper.replyOk(boardVO);
	}
}
