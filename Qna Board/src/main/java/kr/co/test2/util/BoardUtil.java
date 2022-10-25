package kr.co.test2.util;

import kr.co.test2.vo.BoardPagingVO;
import kr.co.test2.vo.BoardSearchVO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BoardUtil {

	private String url;
	private BoardPagingVO boardPagingVO;
	private BoardSearchVO boardSearchVO;
	
	public String getPaging() throws Exception {
		
		System.out.println(this.boardPagingVO.toString());
		
		StringBuilder sb = new StringBuilder();
		if (this.boardPagingVO.getPage()>1) {
			sb.append(String.format("<a href='%s?page=%d&searchopt=%s&searchval=%s'>[처음]</a>", this.getUrl(), this.boardSearchVO.getSearchopt(), this.boardSearchVO.getSearchval() ));
		}
		
		return sb.toString();
	}
	
}
