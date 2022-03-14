package com.homework.account.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.homework.entity.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
		
	
	public Account findByUserAccount(String userAccount);
	
	@Query("SELECT  a.uid FROM Account a WHERE a.userAccount = ?1")
	public int findIdByUserAccount(String userAccount);
}
