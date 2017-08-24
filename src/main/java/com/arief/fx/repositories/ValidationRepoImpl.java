package com.arief.fx.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.arief.fx.domain.Mahasiswa;
import com.arief.fx.domain.User;

@Repository
public class ValidationRepoImpl implements ValidationRepo{

	@Autowired
	private EntityManager em;
	@Autowired
	private NamedParameterJdbcTemplate named;
	
	
	@Override
	public List nimIsExists(String nim) {
		Query q = em.createNativeQuery("select * from user_mhs where nim = :nim");
		q.setParameter("nim", nim);
		
		return q.getResultList();
	}

	@Override
	public boolean cariUsername(String userName) {
		List list = em.createNativeQuery("select username from t_user where username = :name")
				.setParameter("name", userName).getResultList();
		
		int index = list.size();
		
		if(index>=1) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public User validasiUser(String username, String pass) {
		Map<String,String> map = new HashMap<>();
		
		map.put("username", username);
		map.put("pass", pass);
		
		User u = named.queryForObject(
				"select username,password from t_user where username = :username and password = :pass", 
				map, new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet arg0, int arg1) throws SQLException {
						User u = new User();
						u.setUsername(arg0.getString("username"));
						u.setPassword(arg0.getString("password"));
						return u;
					}
				});
		
		return u;
	}



}
