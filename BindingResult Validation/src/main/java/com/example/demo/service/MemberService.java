package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.MemberDTO;

@Service
public class MemberService implements MemberDAO {
	
	@Autowired
	MemberDAO memberDAO;

	@Override
	public Integer Write(MemberDTO memberDTO) {
		return memberDAO.Write(memberDTO);
	}

	@Override
	public MemberDTO View(String uid) {
		return memberDAO.View(uid);
	}

}

