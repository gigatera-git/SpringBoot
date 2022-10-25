package kr.co.test2.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BoardVO {
	
	private Integer idx;
	
	@NotEmpty(message="제목이 없습니다.")
	@Size(max=200)
	private String title;
	
	@NotEmpty(message="내용이 없습니다.")
	private String content;
	
	@NotEmpty(message="이름이 없습니다.")
	@Size(max=30)
	private String uname;
	
	@NotEmpty(message="비밀번호가 없습니다.")
	@Size(max=30)
	private String pwd;
	
	@NotEmpty(message="비밀번호 확인이 없습니다.")
	@Size(max=30)
	private String pwd2;
	
	private Integer ref;
	private Integer restep;
	private Integer relvl;
	private Integer click;
	private LocalDateTime regdate;
	private LocalDateTime moddate;

}
