package com.crtvu.service;

public interface MemberService {

	int updatePassword(int member_id,String password,String newPassword);

	int updateMemberInfo(int member_id,int age,String phone,String email);
}
