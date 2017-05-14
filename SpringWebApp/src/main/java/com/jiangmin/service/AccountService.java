package com.jiangmin.service;

import org.springframework.validation.Errors;

import com.jiangmin.domain.Account;

public interface AccountService {
	boolean registerAccount(
			Account account, String password, Errors errors);
	
}
