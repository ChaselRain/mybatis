package com.hl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hl.model.MUser;
import com.hl.service.UserService;

@RestController
@RequestMapping(value = "/musers")
@Api("使用JPA的方式")
public class MUserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/{name}", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "根据用户名查询用户")
	public MUser findByName(@PathVariable String name) {
		return userService.findByName(name);
	}
	
	
}
