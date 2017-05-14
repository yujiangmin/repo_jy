package com.jiangmin.dao;

import com.jiangmin.domain.Account;

public interface AccountDao extends Dao<Account> {
	
	void create(Account account, String password);

	Account findByUsername(String username);
}
