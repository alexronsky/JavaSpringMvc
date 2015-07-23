package jsmvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsmvc.model.TableItem;
import jsmvc.service.Database;

@Repository("tableItemDao")
public class TableItemDao {
	@Autowired Database db;
	
	public TableItemDao() {
	}
	
	public List<TableItem> getTableItems() {
		Statement stmt = null;
		List<TableItem> items = new ArrayList<>();

		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, TEXT, INSERT_DATE FROM table1"); 
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("TEXT");
				Date date = (Date) rs.getTimestamp("INSERT_DATE");
				items.add(new TableItem(id, name, date));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}
	
	public void deleteItem(int id) {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM table1 WHERE ID = ?";
		try {
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addItem(TableItem item) {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO table1 (ID, TEXT, INSERT_DATE) VALUES (?,?,?)";
		
		try {
			if (item.getId() == 0) {
				item.setId(getNextId());
			}
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setInt(1, item.getId());
			stmt.setString(2, item.getName());
			stmt.setTimestamp(3, new Timestamp(item.getDate().getTime()));
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private int getNextId() throws SQLException {
		Statement stmt = null;
		int nextId = 0;

		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(ID) maxId FROM table1");
			if (rs != null && rs.next()) {
				nextId = rs.getInt("maxId") + 1;
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nextId;
	}
}



