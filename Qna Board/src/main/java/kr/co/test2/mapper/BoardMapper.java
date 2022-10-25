package kr.co.test2.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.test2.vo.BoardPagingVO;
import kr.co.test2.vo.BoardSearchVO;
import kr.co.test2.vo.BoardVO;

@Mapper
public interface BoardMapper {

	//list
	public Map<String, Integer> getPreList(@Param("boardSearchVO") BoardSearchVO boardSearchVO, @Param("boardPagingVO") BoardPagingVO boardPagingVO);
	public List<BoardVO> getList(@Param("boardSearchVO") BoardSearchVO boardSearchVO, @Param("boardPagingVO") BoardPagingVO boardPagingVO);
	
	//view
	public Integer setClick(@Param("idx") Integer idx);
	public Map<String, String> getView(@Param("idx") Integer idx);
	
	//mod
	public Map<String, String> getMod(@Param("idx") Integer idx);
	
	//mod pwd chk
	public Integer getPwdChk(@Param("boardVO") BoardVO boardVO);
	
	//modok
	public Integer getModOk(@Param("boardVO") BoardVO boardVO);
	
	//del
	public Integer delOk(@Param("idx") Integer idx);
	
	// write
	public Integer getRef();
	public Integer writeOk(BoardVO boardVO);
	
	//reply
	public Map<String, Integer> getReGrp(@Param("idx") Integer idx);
	public Integer replyPre(Map<String,Integer> regrp);
	public Integer replyOk(BoardVO boardVO);
	
}
