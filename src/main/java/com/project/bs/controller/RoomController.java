package com.project.bs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.project.bs.service.RoomService;

@Controller
@RequestMapping("/admin")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping("/view-rooms")
	public String viewRooms(Model m) {
		m.addAttribute("allrooms", roomService.getAllRooms());
		return "/room/view-page";
	}

	@PostMapping("/toggle-room-status/{id}")
	public RedirectView toggleRoomStatus(@PathVariable("id") int id) throws Exception {
		roomService.toggleRoomStatus(id);
		return new RedirectView("/admin/view-rooms");
	}
}
