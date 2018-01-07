package com.crtvu.service;

import com.crtvu.dto.MemberJson;
import com.crtvu.entity.Member;

import java.util.List;

public interface MemberService {

	int updatePassword(int member_id,String password,String newPassword);

	int updateMemberInfo(int member_id,int age,String phone,String email);

	int insert(MemberJson memberJson);

	List<Member> getMemberListLimit(int page,String name);

	int getPageCount(String name);

	Member getMember(int id);

	int edit(int id,MemberJson memberJson);

	int delete(int id);
}
