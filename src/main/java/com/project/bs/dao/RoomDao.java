package com.project.bs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bs.entities.Room;

@Repository
public interface RoomDao extends CrudRepository<Room, Integer> {
	public List<Room> findByStatusTrue();

	@Query(value = "select * from room where customer_id = ?", nativeQuery = true)
	public List<Room> findByCustomerId(int id);

	@Query(value = "select count(id) from room where status=true", nativeQuery = true)
	public int findByNoOfActiveRoom();
}
