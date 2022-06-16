package com.project.bs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bs.dao.CustomerDao;
import com.project.bs.dao.RoomDao;
import com.project.bs.entities.Customer;
import com.project.bs.entities.Room;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private RoomDao roomDao;

	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerDao.findAll();
	}

	public List<Customer> getAllCustomersByName(String name) {
		return customerDao.findByNameContaining(name);
	}

	public void toggleCustomerCheckedinStatus(int id) {
		Optional<Customer> findById = customerDao.findById(id);
		if (findById.isPresent()) {

			Customer customer = findById.get();
			List<Room> rooms = customer.getRooms();
			customerDao.delete(customer);
			for (Room room : rooms) {
				room.setStatus(!room.isStatus());
				roomDao.save(room);
			}
		}
	}

	public Customer saveCustomer(Customer customer, int noofroom) throws Exception {
		int noOfActiveRoom = roomDao.findByNoOfActiveRoom();
		if (noOfActiveRoom < noofroom) {
			throw new Exception("no of noom exceded");
		}
		return customerDao.save(customer);
	}

	public Customer assignCustomerRoom(List<Integer> roomnumbers, int customerid) {
		Customer customer = customerDao.findById(customerid).get();
		List<Room> rooms = new ArrayList<>();
		for (int i : roomnumbers) {
			Room room = roomDao.findById(i).get();
			room.setStatus(false);
			roomDao.save(room);
			rooms.add(room);
		}
		customer.setRooms(rooms);
		return customerDao.save(customer);

	}
}
