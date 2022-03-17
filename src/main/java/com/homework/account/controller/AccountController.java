package com.homework.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.homework.account.service.AccountService;
import com.homework.entity.Account;
import com.homework.entity.AccountDetail;
import com.homework.websecurity.AccountUserDetails;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@GetMapping("/login")
	public String login() {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String index(@AuthenticationPrincipal AccountUserDetails loggedAccount ,Model model) {
		Account account=loggedAccount.getLoggedAccount();
		model.addAttribute("account",account);
		return "main";
	}
	
	@GetMapping("/editAccount")
	public String editPage(@AuthenticationPrincipal AccountUserDetails loggedAccount ,Model model) {
		
		Account loggedaccount=loggedAccount.getLoggedAccount();
		Account account=service.findByUserAccount(loggedaccount.getUserAccount());
		AccountDetail accountDetail =account.getAccountDetail();
		model.addAttribute("account",account);
		model.addAttribute("accountDetail",accountDetail);
		return "editaccount";
	}
	
	@GetMapping("/progessDeleteAccount")
	public String processDelete(@AuthenticationPrincipal AccountUserDetails loggedAccount ,Model model) {
		
		Account account=loggedAccount.getLoggedAccount();
		service.deleteAccount(account);
	
		return "redirect:/logout";
	}
	
	@PostMapping("/progessEditAccount")
	public String processEdit(@ModelAttribute("account")Account account ,@ModelAttribute("accountDetail") AccountDetail acd,Model model) {
		account.setAccountDetail(acd);
		service.edit(account);
		
		model.addAttribute("text","修改已完成");
		return "success";
	}
}
