package kr.co.login.vo;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;

@Data
public class MemberVO {

	private String uid;
	private String password;
	private String uname;
	private String tel;
	private String email;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
}
