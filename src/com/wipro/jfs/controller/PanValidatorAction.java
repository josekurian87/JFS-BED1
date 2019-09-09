package com.wipro.jfs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wipro.jfs.entity.PanData;
import com.wipro.jfs.service.VerificationService;

@Controller
public class PanValidatorAction {

	@Autowired
	private VerificationService verificationService;

	@Autowired
	private PanValidator panValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(panValidator);
	}

	/**
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("panData", new PanData());
		return "index";
	}

	/**
	 * 
	 * @param panData
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkEligibility", method = RequestMethod.POST)
	public String user(@ModelAttribute("panData") @Validated PanData panData, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "index";
		}

		String message = "";
		String resultType = "";
		String title = "";

		try {
			if (verificationService.verifyPan(panData.getPanNo())) {
				if (verificationService.verifyCreditScore((panData.getPanNo()))) {
					resultType = "Y";
					message = "Congratulations!!! You are eligible for a credit card";
					title = "CreditCard-Eligible";
				} else {
					resultType = "N";
					message = "Sorry!!! You are not eligible for a credit card";
					title = "CreditCard-Not Eligible";
				}
			} else {
				resultType = "I";
				message = "Invalid PAN Entry !!!";
				title = "CreditCard-Invalid";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("resultType", resultType);
		model.addAttribute("message", message);
		model.addAttribute("title", title);
		return "result";
	}
}
