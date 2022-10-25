package kr.co.test2.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.test2.service.BoardService;
import kr.co.test2.vo.BoardPagingVO;
import kr.co.test2.vo.BoardSearchVO;
import kr.co.test2.vo.BoardVO;


@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	/*
	 list
	 */
	//@ResponseBody
	@GetMapping("/list")
	public String getList(Model model, BoardSearchVO boardSearchVO, BoardPagingVO boardPagingVO) throws Exception 
	{
		Map<String,Integer> pagingMap = new HashMap<String,Integer>(); 
		pagingMap = boardService.getPreList(boardSearchVO, boardPagingVO);   // intTotalPage=4, intTotalCount=33
		boardPagingVO.setInttotalpage( Integer.parseInt(String.valueOf(pagingMap.get("intTotalPage"))) );
		boardPagingVO.setInttotalcount( Integer.parseInt(String.valueOf(pagingMap.get("intTotalCount"))) );
		boardPagingVO.setStartnum( (boardPagingVO.getPage()-1)*boardPagingVO.getIntpagesize() );
	
		String pagination = boardPagingVO.getPagination("/list", boardSearchVO);
		
		List<BoardVO> lists = boardService.getList(boardSearchVO, boardPagingVO);
		//System.out.println(lists.toString());
		model.addAttribute("lists", lists);
		model.addAttribute("pagination", pagination);
		model.addAttribute("boardPagingVO",boardPagingVO);
		model.addAttribute("boardSearchVO", boardSearchVO); 
		
		return "list";	
	}
	
	/*
	 mod
	 */
	@GetMapping("/mod/{idx}")
	public String mod(BoardVO boardVO, @PathVariable("idx") Integer idx,BoardSearchVO boardSearchVO, BoardPagingVO boardPagingVO, Model model) {
		Map<String,String> rs = new HashMap<String,String>();
		rs = boardService.getMod(idx);
		
		boardVO.setIdx(Integer.parseInt(String.valueOf(rs.get("idx"))));
		boardVO.setUname(rs.get("uname"));
		boardVO.setTitle(rs.get("title"));
		boardVO.setContent(rs.get("content"));

		model.addAttribute("boardPagingVO",boardPagingVO);
		model.addAttribute("boardSearchVO", boardSearchVO); 
		
		return "mod";
	}
	
	@PostMapping("/mod/{idx}")
	public String modOk(@Valid BoardVO boardVO, BindingResult bindingResult, @PathVariable("idx") Integer idx, BoardSearchVO boardSearchVO, BoardPagingVO boardPagingVO) throws UnsupportedEncodingException {
		//System.out.println(boardSearchVO.toString());
		//System.out.println(boardPagingVO.toString());
		//validation
		if (!boardVO.getPwd().equals(boardVO.getPwd2())) {
			bindingResult.rejectValue("pwd2", "pwd2", "비밀번호 확인이 다릅니다");
		}
		if (boardService.getPwdChk(boardVO)<1) {
			bindingResult.rejectValue("pwd2", "pwd2", "글 비밀번호가 틀립니다");
		}
		if (boardService.getModOk(boardVO)<1) {
			bindingResult.rejectValue("updateerr", "updateerr" , "업데이트가 되지 않았습니다");
		}
		if (bindingResult.hasErrors()) {
			return "mod";
		}
		
		//save 
		
		return (String.format("redirect:/view/%d?page=%d&searchopt=%s&searchval=%s", boardVO.getIdx(), boardPagingVO.getPage(), boardSearchVO.getSearchopt(), URLEncoder.encode(boardSearchVO.getSearchval(), "UTF-8") ));
	}
	

	/*
	 view
	*/
	@GetMapping("/view/{idx}")
	public String view(@PathVariable("idx") Integer idx, BoardSearchVO boardSearchVO, BoardPagingVO boardPagingVO, Model model) {

		Integer updateCnt = boardService.setClick(idx);
		
		Map<String,String> map = new HashMap<String,String>();
		map = boardService.getView(idx);
		//System.out.println(map.toString());
		
		model.addAttribute("rs", map);
		model.addAttribute("boardPagingVO",boardPagingVO);
		model.addAttribute("boardSearchVO", boardSearchVO); 
		
		//System.out.println("view : " + boardSearchVO.toString());
		
		return "view";
	}
	
	/*
	 del
	 */
	@PostMapping("/del")  // request variable로 변경
	public String del(Integer idx, BoardSearchVO boardSearchVO, BoardPagingVO boardPagingVO, Model model ) {
		
		Integer delCnt = boardService.delOk(idx);
		if (delCnt>0) {
			return (String.format("redirect:/list?page=%d&searchopt=%s&searchval=%s", boardPagingVO.getPage(), boardSearchVO.getSearchopt(), boardSearchVO.getSearchval()));
		} else {
			return (String.format("redirect:/view/%d?page=%d&searchopt=%s&searchval=%s", idx, boardPagingVO.getPage(), boardSearchVO.getSearchopt(), boardSearchVO.getSearchval()));
		}

	}
	
	/*
	 write
	 */
	@GetMapping("/write")
	public String write(BoardVO boardVO) {
		return "write";
	}
	//@ResponseBody
	@PostMapping("/write")
	public String writeOk(@Valid BoardVO boardVO, BindingResult bindingResult ) {
		
		//validation
		if (!boardVO.getPwd().equals(boardVO.getPwd2())) {
			bindingResult.rejectValue("pwd2", "pwd2", "비밀번호 확인이 다릅니다");
		}
		if (bindingResult.hasErrors()) {
			return "write";
		}
		
		boardVO.setRef(boardService.getRef());
		boardVO.setRestep(0);
		boardVO.setRelvl(0);
		
		boardService.writeOk(boardVO);
		Integer idx = boardVO.getIdx();
		if (idx<=0) {
			bindingResult.addError(new ObjectError("error","등록된 글이 없습니다"));
			return "write";
		}
		
		return "redirect:/list";
	}
	
	@GetMapping("/reply/{idx}")
	public String reply(@PathVariable("idx") Integer idx, BoardPagingVO boardPagingVO, BoardSearchVO boardSearchVO, Model model, BoardVO boardVO) {

		Map<String,Integer> map = boardService.getReGrp(idx);
		model.addAttribute("rs", map);
		
		return "reply";
	}

	@PostMapping("/reply/{idx}")
	public String reply(@PathVariable("idx") Integer idx, BoardPagingVO boardPagingVO, BoardSearchVO boardSearchVO, Model model, @Valid BoardVO boardVO, BindingResult bindingResult) {
		
		// return "reply" 시 get하고 같은 내용 만들어주기
		Map<String,Integer> map = boardService.getReGrp(idx);
		model.addAttribute("rs", map);

		//validation/////////////////////////////////////////////////////////////////////
		if (!boardVO.getPwd().equals(boardVO.getPwd2())) {
			bindingResult.rejectValue("pwd2", "pwd2", "비밀번호 확인이 다릅니다");
		}
		Map<String, Integer> regrp = new HashMap<String, Integer>();
		regrp.put("ref", boardVO.getRef());
		regrp.put("restep", boardVO.getRestep());
		regrp.put("relvl", boardVO.getRelvl());
		if (boardService.replyPre(regrp)<0) {
			bindingResult.addError(new ObjectError("error","답변 초기 세팅 오류입니다"));
		}
		if (boardService.replyOk(boardVO)<=0) {
			bindingResult.addError(new ObjectError("error","등록된 답변이 없습니다"));
		}
		if (bindingResult.hasErrors()) {
			return "reply";
		}
		//////////////////////////////////////////////////////////////////////////////////

		return String.format("redirect:/list?page=%d&searchopt=%s&searchval=%s", boardPagingVO.getPage(), boardSearchVO.getSearchopt(), boardSearchVO.getSearchval() );
	}
	
}
