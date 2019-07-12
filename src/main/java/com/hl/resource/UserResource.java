package com.hl.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hl.model.User;
import com.hl.service.UserService;

/*@Path("/jersey")
@Component
@Api("jersey案例")
public class UserResource {

	@Autowired
	private UserService userService;

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "根据id获取用户")
	public User getUserById(@PathParam("id") long id) {
		return userService.getById(id);
	}
}
*/