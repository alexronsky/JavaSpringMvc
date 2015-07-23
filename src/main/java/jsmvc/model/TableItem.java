package jsmvc.model;

import java.util.Date;

public class TableItem {
	private int id;
	private String name;
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	
	
	public TableItem(String name, Date date) {
		this.id = 0;
		this.name = name;
		this.date = date; 
	}
	public TableItem(int id, String name, Date date) {
		this(name, date);
		this.id = id; 
	}
}


