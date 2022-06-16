package com.project.bs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bs.entities.Customer;
import com.project.bs.entities.Room;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {
	List<Customer> findByNameContaining(String name);

	public int countByRooms(Room room);

	@Query(value = "select count(id) from customer where Room = ?", nativeQuery = true)
	public int checkIfRoomHasCustomer(Room room);
}
