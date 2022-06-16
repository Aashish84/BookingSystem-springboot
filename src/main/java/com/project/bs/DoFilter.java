package com.project.bs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class DoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		String string = (String) session.getAttribute("admin");
		if (string == null || string.length() == 0) {
			System.out.println("no session");
			resp.sendRedirect("/");
		}
		if (string != null && !string.equals("helloadmin")) {
			System.out.println("no session 2");
			resp.sendRedirect("/");
		}

		chain.doFilter(request, response);
	}

}
