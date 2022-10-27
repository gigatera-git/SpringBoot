package kr.co.login.controller;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.login.service.MemberService;
import kr.co.login.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberSerivice;

	@GetMapping("/")
	public String index(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails)principal;
			String uid = userDetails.getUsername();

			Optional<MemberVO> mv = this.memberSerivice.getMember(uid);
			MemberVO memberVO = mv.get();
			
			model.addAttribute("memberVO", memberVO);
		}
		
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	

	
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		memberVO.setPassword(new BCryptPasswordEncoder().encode(memberVO.getPassword()));
		memberVO.setCreated_at(LocalDateTime.now());
		memberVO.setUpdated_at(LocalDateTime.now());
		
		this.memberSerivice.JoinOk(memberVO);

		return "redirect:/";
	}
	
}
