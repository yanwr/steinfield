package com.my.steinfield.Steinfield.services;

import com.my.steinfield.Steinfield.models.User;
import com.my.steinfield.Steinfield.security.UserSS;
import java.util.List;

public interface UserService {
	List<User> index() throws Exception;
	User show(Long id);
	User store(User user);
	boolean update(Long id, User user);
	boolean destroy(Long id);
	UserSS authenticated();
}