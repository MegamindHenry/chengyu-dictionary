/**
 * 
 */
package com.colewe.ws1819.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.colewe.ws1819.client.DictionaryService;

/**
 * this service works with dictionary function
 * 
 * 
 * @author jingwen and xuefeng
 *
 */
public class DictionaryServiceImpl extends RemoteServiceServlet implements DictionaryService {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/colewe";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "1234567890";

	@Override
	public void init() throws ServletException {

		try {
			// initialize data base driver
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * search function for dictionary
	 * 
	 * @param
	 * @author Xuefeng and Jingwen
	 */
	@Override
	public ArrayList<String[]> search() {
		//doing the search
		
		ArrayList<String[]> result = new ArrayList<>();
		Connection conn;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			ResultSet rs;
			
			stmt = conn.prepareStatement("select * from Chengyu");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String[] str = {
						rs.getString("ID"),
						rs.getString("Abbr"),
						rs.getString("Chinese"),
						rs.getString("EnglishLiteral"),
						rs.getString("EnglishFigurative"),
						rs.getString("Pinyin"),
						rs.getString("Example"),
						rs.getString("ExampleTranslation"),
						rs.getString("Origin"),
						rs.getString("OriginTranslation"),
						rs.getString("Frequency")};
				result.add(str);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		//return a result in a Arraylist with dictionaryentry
		return result;
	}
}
