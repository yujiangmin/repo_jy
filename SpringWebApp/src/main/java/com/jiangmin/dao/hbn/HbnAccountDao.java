package com.jiangmin.dao.hbn;

import javax.inject.Inject;

import org.hibernate.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jiangmin.dao.AccountDao;
import com.jiangmin.dao.hibernate.AbstractHbnDao;
import com.jiangmin.domain.Account;


@Repository
public class HbnAccountDao extends AbstractHbnDao<Account> implements AccountDao {

	private static final String UPDATE_PASSWORD_SQL =
			"update account set password = ? where username = ?";
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void create(Account account, String password) {
		create(account);
		jdbcTemplate.update(
		UPDATE_PASSWORD_SQL, password, account.getUsername());
	}

	@Override
	public Account findByUsername(String username) {
		Query q = getSession().getNamedQuery("findAccountByUsername");
		q.setParameter("username", username);
		return (Account) q.uniqueResult();
	}

	

}
