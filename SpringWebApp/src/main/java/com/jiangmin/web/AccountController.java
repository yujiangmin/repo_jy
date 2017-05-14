package com.jiangmin.web;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiangmin.domain.Account;
import com.jiangmin.service.AccountService;

@Controller
@RequestMapping(value = "/users")
public class AccountController {

	private static final String VN_REG_FORM = "users/registrationForm";
	private static final String VN_REG_OK = "redirect:registration_ok";
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	
	@Inject
	private AccountService accountService;
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {
		model.addAttribute("account", new AccountForm());
		return "users/registrationForm";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postRegistrationForm(@ModelAttribute("account") @Valid AccountForm form, BindingResult result) {
		log.info("Created registration: {}" + form);
		convertPasswordError(result);
		accountService.registerAccount(
				toAccount(form), form.getPassword(), result);
		log.info("result.hasErrors()"+result.hasErrors());
		return (result.hasErrors() ? VN_REG_FORM : VN_REG_OK);
	}
	
	private static Account toAccount(AccountForm form) {
		Account account = new Account();
		account.setUsername(form.getUsername());
		account.setFirstName(form.getFirstName());
		account.setLastName(form.getLastName());
		account.setEmail(form.getEmail());
		account.setMarketingOk(form.isMarketingOk());
		account.setAcceptTerms(form.getAcceptTerms());
		account.setEnabled(true);
		return account;
		}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "username", "password", "confirmPassword", "firstName", "lastName",
				"email", "marketingOk", "acceptTerms" });
	}

	private static void convertPasswordError(BindingResult result) {
		for (ObjectError error : result.getGlobalErrors()) {
			String msg = error.getDefaultMessage();
			if ("account.password.mismatch.message".equals(msg)) {
				if (!result.hasFieldErrors("password")) {
					result.rejectValue("password", "error.mismatch");
				}
			}
		}
	}
}
