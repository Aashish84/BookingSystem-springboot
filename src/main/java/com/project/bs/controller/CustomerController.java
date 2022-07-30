package com.project.bs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bs.entities.Customer;
import com.project.bs.entities.Room;
import com.project.bs.service.CustomerService;
import com.project.bs.service.RoomService;

@Controller
@RequestMapping("/admin")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RoomService roomService;

	@GetMapping("/all-customers")
	@Transactional
	public String getAllCustomers(Model m) {
		List<Customer> allCustomers = customerService.getAllCustomers();
		System.out.println(allCustomers.get(0).getRooms());
		m.addAttribute("result", allCustomers);
		return "customer/view-page";
	}

	@PostMapping("/customers")
	@Transactional
	public String getAllCustomersByName(@RequestParam("customername") String cusName, Model m) {
		List<Customer> allCustomers = customerService.getAllCustomersByName(cusName);
		System.out.println(allCustomers.get(0).getRooms());
		m.addAttribute("result", allCustomers);
		return "customer/view-page";
	}

	@PostMapping("/customer-checkout/{id}")
	@Transactional
	public String customerCheckout(@PathVariable("id") int id) {
		customerService.toggleCustomerCheckedinStatus(id);
		return "index";
	}

	@GetMapping("/add-customer")
	public String addCustomerView() {
		return "customer/insert-record";
	}

	@PostMapping("/add-customer")
	public String addCustomer(@ModelAttribute Customer customer, @RequestParam("noofroom") int noofroom, Model m)
			throws Exception {
		Customer saveCustomer = customerService.saveCustomer(customer, noofroom);
		List<Room> allActiveRooms = roomService.getAllActiveRooms();
		m.addAttribute("customer", saveCustomer);
		m.addAttribute("activeroom", allActiveRooms);
		m.addAttribute("noofroom", noofroom);
		return "customer/insert-room";
	}

	@PostMapping("/add-customer-room")
	public String addCustomerRoom(@RequestParam("customerid") int cusid, @RequestParam Map<String, String> params,
			Model m) {
		List<Integer> roomnumbers = new ArrayList<>();
		for (Entry<String, String> entry : params.entrySet()) {
			if (!entry.getKey().equals("customerid")) {
				roomnumbers.add(Integer.parseInt(entry.getValue()));
			}
		}
		Customer assignCustomerRoom = customerService.assignCustomerRoom(roomnumbers, cusid);
		m.addAttribute("result", assignCustomerRoom);
		return "customer/view-page";
	}
}
