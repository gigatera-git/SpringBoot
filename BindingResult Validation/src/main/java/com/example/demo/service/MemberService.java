package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	public Integer Write(MemberDTO memberDTO) {
		return memberDAO.Write(memberDTO);
	}
	
	public MemberDTO View(String uid) {
		System.out.println("MemberDTO View uid : " + uid);
		return memberDAO.View(uid);
	}

}
