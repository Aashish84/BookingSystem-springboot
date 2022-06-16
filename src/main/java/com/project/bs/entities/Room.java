package com.project.bs.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
	@Id
	private int id;
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", status=" + status + "]";
	}

}
