package com.crtvu.dao;

import com.crtvu.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDAO{

    int getAuthotity(int role_id);
	int editRole(@Param("id")int id,@Param("name")String name,@Param("description")String description,@Param("authority")int auth);
	int delete(int id);
	List<Role> getAllRole(@Param("name") String name);
	Role getRole(int id);
	int insert(@Param("name")String name,@Param("description")String description,@Param("authority")int authority);
}