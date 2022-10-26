package kr.co.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.co.login.mapper.MemberMapper;
import kr.co.login.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberMapper memberMapper;
	
	public Integer JoinOk(MemberVO memberVO) {
		return this.memberMapper.JoinOk(memberVO);
	}
	
	public Optional<MemberVO> getMember(String uid) {
		return this.memberMapper.getMember(uid);
	}
	
}
