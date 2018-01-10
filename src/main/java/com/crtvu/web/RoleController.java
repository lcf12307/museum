package com.crtvu.web;

import java.util.ArrayList;
import java.util.List;

import com.crtvu.Authority;
import com.crtvu.dto.RoleJson;
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

	@Auth("Member")
	@RequestMapping("/list")
	public String getRoleList(String name,Model model){
		List<Role> roles =  roleService.getAllRole(name);
		List<RoleJson> roleJsons = new ArrayList<>();
		RoleJson roleJson;
		String temp ;
		for(Role role:roles){
			temp="";
			roleJson=new RoleJson(role);
			for(String e:Authority.getAuthorityNames(role.getAuthority())){
				temp+="，"+e;
			}
			if(temp.length()>0)
				temp=temp.substring(1);
			roleJson.setAuthorityName(temp);
			roleJsons.add(roleJson);
		}
		model.addAttribute("roleJsons",roleJsons);
		return "/role/list";
	}

	@Auth("Member")
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
			int code =roleService.insert(name, description, auth);
			if(code>0){
				return R.ok();
			}else if(code==-1)
				return R.error("角色名重复，请换一个！");
		}catch (Exception e){
			e.printStackTrace();
		}
		return R.error();
	}

	@Auth("Member")
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") int id,Model model){
		Role role = roleService.getRole(id);
		model.addAttribute("role",role);
		return "/role/edit";
	}

	@Auth("Member")
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
			int code = roleService.editRole(id,name,description,auth);
			if(code>0)
				return  R.ok();
			else if(code==-1)
				return R.error("角色名重复，请换一个！");
		}catch (Exception e){
			e.printStackTrace();
		}
		return R.error();
	}

	@Auth("Member")
	@RequestMapping("/delete")
	@ResponseBody
	public R delete(int id){
		try{
			int rtn = roleService.delete(id);
			if(rtn>0){
				return R.ok();
			}else if(rtn==-1){
				return R.error("删除失败，存在该角色的用户，不能删除");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return R.error();
	}
}
