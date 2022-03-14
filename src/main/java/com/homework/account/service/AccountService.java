package com.homework.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.homework.account.repository.AccountDetailRepository;
import com.homework.account.repository.AccountRepository;
import com.homework.entity.Account;
import com.homework.entity.AccountDetail;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repo;

	@Autowired
	private AccountDetailRepository detailRepo;

	@Autowired
	private PasswordEncoder bcryptoEncoder;

	public void createUser(Account account, AccountDetail acd) {
		acd.setAccount(account);
		account.setAccountDetail(acd);

		String userAccount = account.getUserAccount();
		if (repo.findByUserAccount(userAccount) == null && detailRepo.findByUserEmail(acd.getUserEmail())== null) {
			String encodePassword = bcryptoEncoder.encode(account.getUserPassword());
			account.setUserPassword(encodePassword);
			repo.save(account);
		}else {
			throw new RuntimeException("duplicated ");
		}

	}

	public void edit(Account account) {
		String accountName = account.getUserAccount();

		Account databaseAccount = repo.findByUserAccount(accountName);
		String pwd = account.getUserPassword().trim();
		if (pwd != "" & pwd != null) {

			databaseAccount.setUserPassword(bcryptoEncoder.encode(pwd));
		}
		AccountDetail newaccountDetail = account.getAccountDetail();
		newaccountDetail.setUid(databaseAccount.getUid());
		databaseAccount.setAccountDetail(newaccountDetail);
		repo.save(databaseAccount);

	}

	public boolean activeAccount(String code, String userEmail) {
		AccountDetail acd = detailRepo.findByUserEmail(userEmail);
		Account user = repo.findById(acd.getUid()).get();
		if (code.equals(user.getVerificationcode())) {
			user.setEmailAuth(true);
			repo.save(user);
			return true;
		}
		return false;
	}

	public Account findByUserAccount(String userAccount) {
		return repo.findByUserAccount(userAccount);
	}

	public boolean deleteAccount(Account userAccount) {
		try {
			int id = userAccount.getUid();
			repo.deleteById(id);
			detailRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void save(Account acc) {
		repo.save(acc);

	}
}
