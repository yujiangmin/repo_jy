package com.jiangmin.dao;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserDetailsDao {
	@Inject
	private JdbcTemplate jdbcTemplate;
	private static final String FIND_PASSWORD_SQL = "select password from account where username = ?";

	public String findPasswordByUsername(String username) {
		return jdbcTemplate.queryForObject(FIND_PASSWORD_SQL, new Object[] { username }, String.class);
	}
}
