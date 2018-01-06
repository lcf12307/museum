package com.crtvu.web;

import java.util.List;

import com.crtvu.entity.Role;
import com.crtvu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.crtvu.auth.Auth;
import com.crtvu.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/list")
	public String getRoleList(String name,Model model){
		List<Role> roles =  roleService.getAllRole(name);
		model.addAttribute("roleList",roles);
		return "/role/list";
	}

	@RequestMapping("/add")
	public String add(){
		return "/role/add";
	}

	@RequestMapping("/addProcess")
	@ResponseBody
	public R addRoleProcess(String name,String description,int[] authority){
		try{
			if(authority==null)
				return R.error("请选择权限");
			int auth = 0;
			for(int temp :authority){
				auth+=temp;
			}
			if(roleService.insert(name, description, auth)>0){
				return R.ok();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return R.ok();
	}

	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") int id,Model model){
		Role role = roleService.getRole(id);
		model.addAttribute("role",role);
		return "/role/edit";
	}

	@RequestMapping("editProcess/{id}")
	@ResponseBody
	public R editProcess(@PathVariable("id") int id,String name,String description,int[] authority){
		try{
			if(authority==null)
				return R.error("请选择权限");
			int auth = 0;
			for(int temp :authority){
				auth+=temp;
			}
			if(roleService.editRole(id,name,description,auth)>0)
				return  R.ok();
			else
				return R.error("修改失败");
		}catch (Exception e){
			e.printStackTrace();
			return R.error();
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public R delete(int id){
		try{
			if(roleService.delete(id)>0){
				return R.ok();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return R.error();
	}
}
