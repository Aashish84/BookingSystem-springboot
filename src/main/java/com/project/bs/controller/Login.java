package com.project.bs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class Login {
	@GetMapping
	public String login() {
		return "/login";
	}

	@PostMapping("/login")
	public RedirectView doLogin(@RequestParam("username") String user, @RequestParam("password") String pwd,
			HttpServletRequest request) {
		if (user.equals(pwd) && user.equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", "helloadmin");
		}
		return new RedirectView("/admin");
	}

	@GetMapping("/logout")
	public RedirectView dologOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new RedirectView("/admin");
	}
}
