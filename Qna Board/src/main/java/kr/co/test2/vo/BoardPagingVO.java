package kr.co.test2.vo;

import lombok.Data;

@Data
public class BoardPagingVO {

	private final Integer intpagesize = 10;
	private final Integer intblockpage = 10;
	
	private Integer startnum;
	private Integer page;
	private Integer inttotalcount;
	private Integer inttotalpage;
	
	public BoardPagingVO() {
		if (this.getStartnum()==null) {
			this.setStartnum(0);
		}
		if (this.getPage()==null) {
			this.setPage(1);
		}
		if (this.getInttotalcount()==null) {
			this.setInttotalcount(0);
		}
		if (this.getInttotalpage()==null) {
			this.setInttotalpage(0);
		}
	}

	
	public String getPagination(String url, BoardSearchVO boardSearchVO) {
		
		StringBuilder sb = new StringBuilder();
		
		if (this.getPage()>1) {
			sb.append(String.format("<a href='%s?page=%d&searchopt=%s&searchval=%s'>[처음]</a>", url, 1, boardSearchVO.getSearchopt(), boardSearchVO.getSearchval()));
		} else {
			sb.append("[처음]");  
		}
		
		Integer intTemp = ((this.getPage() - 1) / this.getIntblockpage()) * this.getIntblockpage() + 1;
		if (intTemp==1) {
			sb.append("[이전]");
		} else {
			sb.append(String.format("<a href='%s?page=%d&searchopt=%s&searchval=%s'>[이전]</a>", url, (intTemp - this.getIntblockpage()) , boardSearchVO.getSearchopt(), boardSearchVO.getSearchval()));
		}
		
		Integer intLoop = 1;
		while (intLoop <= this.getIntblockpage() && intTemp <= this.getInttotalpage()) {
			if (intTemp==this.getPage()) {
				sb.append(String.format("<b>%d</b>", intTemp));
			} else {
				sb.append(String.format("<span><a href='%s?page=%d&searchopt=%s&searchval=%s'>%d</a><span>", url, intTemp , boardSearchVO.getSearchopt(), boardSearchVO.getSearchval(), intTemp));
			}
			
			intTemp++;
			intLoop++;
		}
		
		if (intTemp>this.getInttotalpage()) {
			sb.append("[다음]");
		} else {
			sb.append(String.format("<a href='%s?page=%d&searchopt=%s&searchval=%s'>[다음]</a>", url, intTemp , boardSearchVO.getSearchopt(), boardSearchVO.getSearchval()));
		}
		
		if (this.getPage()<this.getInttotalpage()) {
			sb.append(String.format("<a href='%s?page=%d&searchopt=%s&searchval=%s'>[마지막]</a>", url, this.getInttotalpage() , boardSearchVO.getSearchopt(), boardSearchVO.getSearchval()));
		} else {
			sb.append("[마지막]");
		}
		
		return sb.toString();
	}



	
}