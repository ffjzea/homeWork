package com.homework.account.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.homework.entity.AccountDetail;

public interface AccountDetailRepository extends PagingAndSortingRepository<AccountDetail, Integer> {

	public AccountDetail findByUserEmail(String useremail);
}
