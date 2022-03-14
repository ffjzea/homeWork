package com.homework.websecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.homework.account.repository.AccountRepository;
import com.homework.entity.Account;

public class AccountUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountRepository repo;
	@Override
	public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
		
		
	Account user =repo.findByUserAccount(userAccount);
	if (user != null) {
		return new AccountUserDetails(user);
	}
		throw new UsernameNotFoundException("could not found"+userAccount);
	}

}
