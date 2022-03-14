package com.homework.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity @Table(name = "account")
@Component("account")
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id @Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	
	@Column(name = "USERACCOUNT",unique = true)
	private String userAccount;
	
	@Column(name = "USERPASSWORD")
	private String userPassword;
	
	@Column(name = "USERSALT")
	private String userSalt;
	
	@Enumerated(EnumType.STRING)
	@Column
	private AccountLevel accountLevel =AccountLevel.Student;
	
	@Column
	private boolean emailAuth=false;
	
	@Column
	private String verificationcode;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
	private AccountDetail accountDetail;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	public AccountDetail getAccountDetail() {
		return accountDetail;
	}

	public boolean isEmailAuth() {
		return emailAuth;
	}

	public void setEmailAuth(boolean emailAuth) {
		this.emailAuth = emailAuth;
	}

	public void setAccountDetail(AccountDetail accountDetail) {
		this.accountDetail = accountDetail;
	}

	public AccountLevel getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(AccountLevel accountLevel) {
		this.accountLevel = accountLevel;
	}

	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}
	
}
