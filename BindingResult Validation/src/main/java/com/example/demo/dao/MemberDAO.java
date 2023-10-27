package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	
	public Integer Write(MemberDTO memberDTO);
	
	public MemberDTO View(String uid);
	
}
