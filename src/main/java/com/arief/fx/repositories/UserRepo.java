package com.arief.fx.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arief.fx.domain.User;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
	public List<User> findAll();
	public User findByUsername(String userName);
}
