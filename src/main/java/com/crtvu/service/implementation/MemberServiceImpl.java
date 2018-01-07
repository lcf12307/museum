package com.crtvu.service.implementation;

import com.crtvu.dao.MemberDAO;
import com.crtvu.dto.MemberJson;
import com.crtvu.entity.Member;
import com.crtvu.service.MemberService;
import com.crtvu.utils.MuseumContants;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

	private static final int pageNumber = 10;//每页条数

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

	@Override
	public int insert(MemberJson memberJson) {
		if(memberDAO.checkName(-1,StringEscapeUtils.escapeSql(memberJson.getName()))>0)
			return -1;
		return memberDAO.insert(StringEscapeUtils.escapeSql(memberJson.getName()),
				memberJson.getAge(),
				memberJson.getPhone(),
				memberJson.getEmail(),
				DigestUtils.md5Hex(MuseumContants.SALT_FOR_MEMBER+memberJson.getPassword()),
				memberJson.getRole());
	}

	@Override
	public Member getMember(int id) {
		return memberDAO.getMemberInfo(id);
	}

	@Override
	public int edit(int id, MemberJson memberJson) {
		return memberDAO.edit(
				id,
				memberJson.getName(),
				memberJson.getAge(),
				memberJson.getPhone(),
				memberJson.getEmail(),
				DigestUtils.md5Hex(MuseumContants.SALT_FOR_MEMBER+memberJson.getPassword()),
				memberJson.getRole()
		);
	}

	@Override
	public List<Member> getMemberListLimit(int page, String name) {
		if(page<=0||page>getPageCount(name)) {
			return null;
		}
		return memberDAO.getMemberListLimit(name,(page-1)*pageNumber,pageNumber);
	}

	@Override
	public int getPageCount(String name) {
		int count = memberDAO.getAllCount(name);
		int page_count = count == 0 ? 1 : count / pageNumber + (count % pageNumber > 0 ? 1 : 0);
		return page_count;
	}

	@Override
	public int delete(int id) {
		return memberDAO.delete(id);
	}
}
