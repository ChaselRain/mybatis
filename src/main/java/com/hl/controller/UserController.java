package com.hl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.hl.model.User;
import com.hl.service.UserService;

@RestController
@RequestMapping(value = "/users")
@Api("使用mybatis")
public class UserController {
	@Autowired
	private UserService userService;	

	@RequestMapping(value = "/name/{name}", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "姓名模糊查询用户")
	public List<User> likeName(@PathVariable String name) {
		PageHelper.startPage(1, 10);
		return userService.likeName(name);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "根据id查询用户")
	public User getById(@PathVariable Long id) {
		PageHelper.startPage(1, 10);
		return userService.getById(id);
	}

	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "分页查询用户")
	public List<User> getUsers(@RequestParam(defaultValue = "10") int offset, @RequestParam(defaultValue = "1") int limit) {
		PageHelper.startPage(limit, offset);
		return userService.getUsers();
	}
	
}
