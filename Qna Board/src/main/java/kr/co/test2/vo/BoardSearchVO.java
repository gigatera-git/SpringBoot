package kr.co.test2.vo;

import lombok.Data;

@Data
public class BoardSearchVO {
	
	private String searchopt;
	private String searchval;

	public BoardSearchVO() {
		//super();
		if (this.getSearchopt()==null) {
			this.setSearchopt("");
		}
		if (this.getSearchval()==null) {
			this.setSearchval("");
		}
	}

	
}
