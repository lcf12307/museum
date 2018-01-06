package com.crtvu.service.implementation;

import com.crtvu.dao.MemberDAO;
import com.crtvu.service.MemberService;
import com.crtvu.utils.MuseumContants;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDAO memberDAO;
	
	public int updatePassword(int member_id, String password, String newPassword) {
		password = DigestUtils.md5Hex(MuseumContants.SALT_FOR_MEMBER+password);
		newPassword = DigestUtils.md5Hex(MuseumContants.SALT_FOR_MEMBER+newPassword);
		return memberDAO.updatePassword(member_id,password,newPassword);
	}
	
	public int updateMemberInfo(int member_id, int age, String phone, String email) {
		return memberDAO.updateMemberInfo(member_id, age, StringEscapeUtils.escapeSql(phone),  StringEscapeUtils.escapeSql(email));
	}
}
