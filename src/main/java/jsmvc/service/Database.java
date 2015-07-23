package jsmvc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class Database {
	private Connection conn;
	
	public Connection getConnection() {
		return conn;
	}

	public Database() {		
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() throws Exception {
		Statement stmt = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/test2", "sa", "sa");
			
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS table1");
			stmt.executeUpdate("CREATE TABLE table1 (ID INTEGER, TEXT VARCHAR(128), INSERT_DATE TIMESTAMP)");
			stmt.executeUpdate("INSERT INTO table1 (ID, TEXT, INSERT_DATE) VALUES (1, 'java.lang', '2015-07-01 11:01:55')");
			stmt.executeUpdate("INSERT INTO table1 (ID, TEXT, INSERT_DATE) VALUES (2, 'java.io', '2015-07-02 12:01:55')");
			stmt.executeUpdate("INSERT INTO table1 (ID, TEXT, INSERT_DATE) VALUES (3, 'java.util', '2015-07-03 13:01:55')");
			stmt.executeUpdate("INSERT INTO table1 (ID, TEXT, INSERT_DATE) VALUES (4, 'java.net', '2015-07-04 14:01:55')");
			stmt.executeUpdate("INSERT INTO table1 (ID, TEXT, INSERT_DATE) VALUES (5, 'java.text', '2015-07-05 15:01:55')");
			stmt.executeUpdate("INSERT INTO table1 (ID, TEXT, INSERT_DATE) VALUES (6, 'javax.xml', '2015-07-06 16:01:55')");
			stmt.executeUpdate("INSERT INTO table1 (ID, TEXT, INSERT_DATE) VALUES (7, 'javax.sql', '2015-07-07 17:01:55')");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
}



