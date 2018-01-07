package com.crtvu.dao;

import com.crtvu.entity.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Jixw on 2017/12/27.
 */
public interface MemberDAO {
    Member checkLogin(@Param("name") String username,@Param("password") String password);
    Member getMemberInfo(int id);
    int updatePassword(@Param("id") int member_id,@Param("password") String password,@Param("newpassword") String newpassword);
    int updateMemberInfo(@Param("id")int id,@Param("age")int age,@Param("phone") String phone,@Param("email") String email);
    int insert(@Param("name") String name,
               @Param("age")int age,
               @Param("phone") String phone,
               @Param("email") String email,
               @Param("password") String password,
               @Param("role") int role);
    int edit(@Param("id") int id,
             @Param("name") String name,
             @Param("age")int age,
             @Param("phone") String phone,
             @Param("email") String email,
             @Param("password") String password,
             @Param("role") int role);
    int delete(int id);
    List<Member> getMemberListLimit(@Param("name") String name, @Param("index") int index, @Param("count") int count);
    int getAllCount(String name);
    int getMemberCountByRole(@Param("role")int role);

    /**
     * ID 为-1时候 检查新增，ID>0时检查修改
     * @param id
     * @param name
     * @return
     */
    int checkName(@Param("id") int id,@Param("name") String name);
}
