package com.project.bs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bs.dao.CustomerDao;
import com.project.bs.dao.RoomDao;
import com.project.bs.entities.Room;

@Service
public class RoomService {
	@Autowired
	private RoomDao roomDao;

	@Autowired
	private CustomerDao customerDao;

	public List<Room> getAllRooms() {
		Iterable<Room> findAll = roomDao.findAll();
		return (List<Room>) findAll;
	}

	public List<Room> getAllActiveRooms() {
		return roomDao.findByStatusTrue();
	}

	public void toggleRoomStatus(int id) throws Exception {
		Optional<Room> findById = roomDao.findById(id);
		if (findById.isPresent()) {
			Room room = findById.get();
			if (room.isStatus() == false) {
				int countByRooms = customerDao.countByRooms(room);
				if (countByRooms != 0) {
					throw new Exception("cannot change status customer is assigned to it");
				}
			}
			room.setStatus(!room.isStatus());
			roomDao.save(room);
		}
	}
}
