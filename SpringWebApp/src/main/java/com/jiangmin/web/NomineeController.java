package com.jiangmin.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiangmin.model.Member;

@Controller
@RequestMapping(value="/nominee")
public class NomineeController {
	private static final Logger log = LoggerFactory.getLogger(NomineeController.class);
	private String thanksViewName = "/nominee/thanks";

	public void setThanksViewName(String thanksViewName) {
		this.thanksViewName = thanksViewName;
	}

	@RequestMapping(value="form", method = RequestMethod.GET)
	public String form(Model model) {
		Member m = new Member();
		m.setFirstName("Yu");
		model.addAttribute("nominee", m);
		return "/nominee/form";
	}

	@RequestMapping(value="form", method = RequestMethod.POST)
	public String processFormData(@ModelAttribute("nominee") Member member, Model model) {
		log.info("Processing nominee : " + member);
		Map map = model.asMap();
		log.info("model[member]=" + map.get("member"));
		log.info("model[nominee]=" + map.get("nominee"));
		return thanksViewName;
	}
}
