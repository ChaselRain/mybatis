package com.hl.service;

import java.util.List;

import com.hl.model.MUser;
import com.hl.model.User;

public interface UserService {

	List<User> likeName(String name);

	User getById(Long id);

	List<User> getUsers();
	

	MUser findByName(String name);

	MUser findByNameAndAge(String name, Integer age);

	/*MUser findUser(String name);*/

}
