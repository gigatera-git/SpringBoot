package kr.co.login.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.co.login.vo.MemberVO;

@Mapper
public interface MemberMapper {

	public Integer JoinOk(MemberVO memberVO);
	
	public Optional<MemberVO> getMember(String uid);
	
}
