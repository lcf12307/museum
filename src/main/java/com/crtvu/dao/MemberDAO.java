package com.crtvu.dao;

import com.crtvu.entity.Member;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Jixw on 2017/12/27.
 */
public interface MemberDAO {
    Member checkLogin(@Param("name") String username,@Param("password") String password);
    Member getMemberInfo(int id);
    int updatePassword(@Param("id") int member_id,@Param("password") String password,@Param("newpassword") String newpassword);
    int updateMemberInfo(@Param("id")int id,@Param("age")int age,@Param("phone") String phone,@Param("email") String email);
}
