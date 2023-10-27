package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

	Integer id;
	
	@NotBlank(message="아이디가 없습니다!!")
	@Pattern(regexp = "^[a-zA-Z0-9]{6,20}$",message = "아이디는 영문+숫자로 6~20자리이어야 합니다.")
	String uid;
	
	@NotBlank(message="비밀번호가 없습니다!!")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$",message = "비밀번호는 영문+숫자+특수기호로 8~20자리이어야 합니다.")
	String password;
	
	@NotBlank(message="비밀번호 확인이 없습니다!!")
	String password2;
	
	@NotBlank(message="이름이 없습니다!!")
	String uname;
	
	@NotBlank(message="핸드폰이 없습니다!!")
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바르지 않은 핸드폰 번호입니다.")
	String tel;
	
	@NotBlank(message="이메일이 없습니다!!")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$",message = "올바르지 않은 이메일입니다.")
	String email;
	
	LocalDateTime created_at;
	LocalDateTime updated_at;
	LocalDateTime deleted_at;
	
}


