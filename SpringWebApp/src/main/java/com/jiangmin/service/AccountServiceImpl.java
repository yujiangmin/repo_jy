package com.jiangmin.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.jiangmin.dao.AccountDao;
import com.jiangmin.domain.Account;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

	@Inject
	private AccountDao accountDao;

	@Transactional(readOnly = false)
	public boolean registerAccount(Account account, String password, Errors errors) {
		validateUsername(account.getUsername(), errors);
		boolean valid = !errors.hasErrors();
		if (valid) {
			accountDao.create(account, password);
		}
		return valid;
	}

	private void validateUsername(String username, Errors errors) {
		if (accountDao.findByUsername(username) != null) {
			errors.rejectValue("username", "error.duplicate", new String[] { username }, null);
		}
	}

}
