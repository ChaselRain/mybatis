package com.hl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hl.mapper.UserMapper;
import com.hl.model.MUser;
import com.hl.model.User;
import com.hl.repository.UserRepository;
import com.hl.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	
	//使用mybatis
	public List<User> likeName(String name) {
		return userMapper.likeName(name);
	}

	public User getById(Long id) {
		return userMapper.getById(id);
	}

	public List<User> getUsers() {
		return userMapper.getUsers();
	}
	
	
	//使用JPA
	public MUser findByName(String name){
		return userRepository.findByName(name);
	}
	
	public MUser findByNameAndAge(String name, Integer age){
		return userRepository.findByNameAndAge(name, age);
	}

	/*public MUser findUser(String name){
		return userRepository.findUser(name);
	}*/

}
