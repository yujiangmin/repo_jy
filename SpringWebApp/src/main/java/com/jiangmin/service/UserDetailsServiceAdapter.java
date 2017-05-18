package com.jiangmin.service;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangmin.domain.Account;
import com.jiangmin.domain.UserDetailsAdapter;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceAdapter implements UserDetailsService {

	@Inject
	AccountService accountService;
	@Inject
	UserDetailsDao userDetailsDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Account account = accountService.getAccountByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("No such user: " + username);
		} else if (account.getRoles().isEmpty()) {
			throw new UsernameNotFoundException("User " + username + " has no authorities");
		}
		UserDetailsAdapter user = new UserDetailsAdapter(account);
		user.setPassword(userDetailsDao.findPasswordByUsername(username));
		return user;
	}

}
