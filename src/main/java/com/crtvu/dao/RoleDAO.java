package com.crtvu.dao;

import com.crtvu.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface RoleDAO{

    int getAuthotity(int role_id);
	int editRole(@Param("id")int id,@Param("name")String name,@Param("description")String description,@Param("authority")int auth);
	int delete(int id);
	List<Role> getAllRole(@Param("name") String name);
	Role getRole(int id);
	int insert(@Param("name")String name,@Param("description")String description,@Param("authority")int authority);

	/**
	 * ID 为-1时候 检查新增，ID>0时检查修改
	 * @param id
	 * @param name
	 * @return
	 */
	int checkName(@Param("id") int id,@Param("name") String name);
}