package com.homework.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.homework.account.service.AccountService;
import com.homework.entity.Account;
import com.homework.entity.AccountDetail;
import com.homework.util.UtilityTool;

@Controller
public class RegisterController {

	@Autowired
	private AccountService service;

	@GetMapping("/register")
	public String registerPage(Model model) {
		Account account = new Account();
		AccountDetail accountDetail = new AccountDetail();
		model.addAttribute("Account", account);
		model.addAttribute("AccountDetail", accountDetail);
		return "register";
	}

	@PostMapping("/createUser")
	public String createUser(@ModelAttribute("Account") Account acc, @ModelAttribute("AccountDetail") AccountDetail acd,
			Model model, HttpServletRequest request) {
		try {
			service.createUser(acc, acd);
			UtilityTool.sendVerificationEmail(acc, request);
			service.save(acc);
			model.addAttribute("text", "註冊完成 請收取認證信  完成帳號啟動 ");
			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			Account account = new Account();
			AccountDetail accountDetail = new AccountDetail();
			model.addAttribute("Account", account);
			model.addAttribute("AccountDetail", accountDetail);
			model.addAttribute("text", "帳號或信箱已被註冊 請再試一次");
			return "register";
		}
	}

	@GetMapping("/verify")
	public String verfy(@RequestParam(name = "code") String code, @RequestParam(name = "email") String email,
			Model model) {
		service.activeAccount(code, email);

		String text = " verify  completed  your account is avaliable ";
		model.addAttribute("text", text);
		return "success";
	}

}
