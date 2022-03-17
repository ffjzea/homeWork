package com.homework.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Entity @Table(name = "ACCOUNT_DETAIL")
@Component("accountDetail")
public class AccountDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name="property", value="account"))
	@Id
	@Column(name="UID")
	@GeneratedValue(generator = "generator")
	private int uid;
	
	@Column(name = "USERGENDER")
	private String userGender;
	
	@Column(name = "USERADDRESS")
	private String userAddress;
	
	@Column(name = "USERPHONE")
	private String userPhone;
	
	@Column(name = "USEREMAIL")
	private String userEmail;
	
	@Column(name = "USERBIRTHDAY")
	private Date userBirthday;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Account account;

	public int getUid() {
		return uid;
	}

	public void setUid(int userid) {
		this.uid = userid;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
}
