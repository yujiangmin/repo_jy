package com.jiangmin.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jiangmin.model.Member;

@Controller
public class RosterController {
	private List<Member> members = new ArrayList<Member>();

	public RosterController() {
		members.add(new Member("John", "Lennon"));
		members.add(new Member("Paul", "McCartney"));
		members.add(new Member("George", "Harrison"));
		members.add(new Member("Ringo", "Starr"));
	}

	@RequestMapping
	public void list(Model model) {
		model.addAttribute("memberList",members);
	}

	@RequestMapping
	public void member(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("member",members.get(id));
	}

}
