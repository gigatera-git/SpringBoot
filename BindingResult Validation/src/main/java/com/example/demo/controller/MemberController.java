package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

import jakarta.validation.Valid;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping(value="/login")
	public String Login() {
		return "login";
	}
	
	@GetMapping(value="/error")
	public String error() {
		return "error";
	}
	@GetMapping(value="/ok")
	public String ok() {
		return "ok";
	}
	
	@GetMapping(value="/join") 
	public String Join(Model model) {
		model.addAttribute("memberDTO", new MemberDTO());
		return "join";
	}
	
	@PostMapping(value="/join")
	public String JoinOk(@Valid MemberDTO memberDTO, BindingResult bindingResult) {

		String password = memberDTO.getPassword();
		String password2 = memberDTO.getPassword2();
		if (!password.equals(password2)) {
			bindingResult.addError(new FieldError("memberDTO","password2","비밀번호 확인이 다릅니다"));
		}
		
		/*
		 * 중복아이디인지, 중복이메일인지, 중복 핸드폰 등등 추가 하면 된다..
		 */
		
		if (bindingResult.hasErrors()) {
			return "join";
		}

		/*
		 * validation 통과후 저장
		 * SpringSecurity 로그인에 대비해서 BCryptPasswordEncoder를 이용하여 암호 생성 
		 */
		memberDTO.setPassword(new BCryptPasswordEncoder().encode(memberDTO.getPassword()));
		memberDTO.setCreated_at(LocalDateTime.now());
		memberDTO.setUpdated_at(LocalDateTime.now());
		
		memberService.Write(memberDTO);
		Integer newid = memberDTO.getId();

		return "ok";

	}

}
