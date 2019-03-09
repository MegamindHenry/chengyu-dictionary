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
import com.colewe.ws1819.shared.Entry;

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
	static final String DB_URL = "jdbc:mysql://localhost/colewe?useUnicode=yes&characterEncoding=UTF-8";

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
	public ArrayList<Entry> search(String target, int mode) {
		//doing the search
		
		ArrayList<Entry> result = new ArrayList<Entry>();
		Connection conn;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			ResultSet rs;
			
			switch(mode) {
				case 1:
					stmt = conn.prepareStatement("select * from Chengyu as c "
							+ "left join ChengyuTag as ct "
							+ "on ct.ChengyuID = c.ID "
							+ "left join Tags as t "
							+ "on t.ID = ct.TagID "
							+ "where Chinese like ?");
					stmt.setString(1, "%" + target + "%");
					break;
				case 2:
					stmt = conn.prepareStatement("select * from Chengyu as c "
							+ "left join ChengyuTag as ct "
							+ "on ct.ChengyuID = c.ID "
							+ "left join Tags as t "
							+ "on t.ID = ct.TagID "
							+ "where Pinyin like ?");
					stmt.setString(1, "%" + target + "%");
					break;
				case 3:
					stmt = conn.prepareStatement("select * from Chengyu as c"
							+ "left join ChengyuTag as ct "
							+ "on ct.ChengyuID = c.ID "
							+ "left join Tags as t "
							+ "on t.ID = ct.TagID "
							+ "where EnglishLiteral like ?");
					stmt.setString(1, "%" + target + "%");
					break;
				default:
					stmt = conn.prepareStatement("select * from Chengyu as c"
							+ "left join ChengyuTag as ct "
							+ "on ct.ChengyuID = c.ID "
							+ "left join Tags as t "
							+ "on t.ID = ct.TagID "
							+ "where Chinese like ?");
					stmt.setString(1, "%" + target + "%");
					break;
			}
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String tag = rs.getString("Tag");
				
				Entry entry = new Entry(rs.getString("ID"), rs.getString("Abbr"), rs.getString("Chinese"),
						rs.getString("EnglishLiteral"), rs.getString("EnglishFigurative"), rs.getString("Pinyin"),
						rs.getString("Example"), rs.getString("ExampleTranslation"), rs.getString("Origin"),
						rs.getString("OriginTranslation"), rs.getString("Frequency"));
						
//				if(result.contains(entry)) {
//					entry = result.get(result.indexOf(entry));
//					if(tag != null) {
//						entry.addTag(tag);
//					}
//				}else {
//					if(tag != null) {
//						entry.addTag(tag);
//					}
//					result.add(entry);
//				}
				
				boolean found = false;
				for(int i=0; i<result.size();i++) {
					Entry eachEntry = result.get(i);
					if (eachEntry.getId().equals(rs.getString("ID"))) {
						if(tag != null) {
							eachEntry.addTag(tag);
						}
						found = true;
						break;
					}
				}
				
				if(!found) {
					if(tag != null) {
						entry.addTag(tag);
					}
					result.add(entry);
				}
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		//return a result in a Arraylist with dictionaryentry
		return result;
	}
	
//	/**
//	 * search function for dictionary
//	 * 
//	 * @param
//	 * @author Xuefeng and Jingwen
//	 */
//	@Override
//	public ArrayList<Entry> tagSearch(String tagID) {
//		//doing the search
//		
//		ArrayList<Entry> result = new ArrayList<>();
//		Connection conn;
//		PreparedStatement stmt = null;
//		
//		try {
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			
//			ResultSet rs;
//			
//			stmt = conn.prepareStatement("select * from Chengyu as c " + 
//					"left join ChengyuTag as ct " + 
//					"on c.ID = ct.ChengyuID " + 
//					"left join Tags as t " + 
//					"on t.ID = ct.TagID " + 
//					"where ct.TagID = ?");
//			stmt.setString(1, tagID);
//			
//			rs = stmt.executeQuery();
//			
//			while (rs.next()) {
//				String tag = rs.getString("Tag");
//				
//				Entry entry = new Entry(rs.getString("ID"), rs.getString("Abbr"), rs.getString("Chinese"),
//						rs.getString("EnglishLiteral"), rs.getString("EnglishFigurative"), rs.getString("Pinyin"),
//						rs.getString("Example"), rs.getString("ExampleTranslation"), rs.getString("Origin"),
//						rs.getString("OriginTranslation"), rs.getString("Frequency"));
//				
//				if(result.contains(entry)) {
//					entry = result.get(result.indexOf(entry));
//					if(tag != null) {
//						entry.addTag(tag);
//					}
//				}else {
//					if(tag != null) {
//						entry.addTag(tag);
//					}
//					result.add(entry);
//				}
//				
//				
//			}
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//		//return a result in a Arraylist with dictionaryentry
//		return result;
//	}
	
	/**
	 * search function for dictionary
	 * 
	 * @param
	 * @author Xuefeng and Jingwen
	 */
	@Override
	public ArrayList<Entry> tagSearch(String target, int mode, ArrayList<String> tags) {
		//doing the search
		
		ArrayList<Entry> result = new ArrayList<Entry>();
		ArrayList<Entry> tagResults = new ArrayList<Entry>();
		Connection conn;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			ResultSet rs;
			
			switch(mode) {
				case 1:
					stmt = conn.prepareStatement("select * from Chengyu as c "
							+ "left join ChengyuTag as ct "
							+ "on ct.ChengyuID = c.ID "
							+ "left join Tags as t "
							+ "on t.ID = ct.TagID "
							+ "where Chinese like ?");
					stmt.setString(1, "%" + target + "%");
					break;
				case 2:
					stmt = conn.prepareStatement("select * from Chengyu as c "
							+ "left join ChengyuTag as ct "
							+ "on ct.ChengyuID = c.ID "
							+ "left join Tags as t "
							+ "on t.ID = ct.TagID "
							+ "where Pinyin like ?");
					stmt.setString(1, "%" + target + "%");
					break;
				case 3:
					stmt = conn.prepareStatement("select * from Chengyu as c"
							+ "left join ChengyuTag as ct "
							+ "on ct.ChengyuID = c.ID "
							+ "left join Tags as t "
							+ "on t.ID = ct.TagID "
							+ "where EnglishLiteral like ?");
					stmt.setString(1, "%" + target + "%");
					break;
				default:
					stmt = conn.prepareStatement("select * from Chengyu as c"
							+ "left join ChengyuTag as ct "
							+ "on ct.ChengyuID = c.ID "
							+ "left join Tags as t "
							+ "on t.ID = ct.TagID "
							+ "where Chinese like ?");
					stmt.setString(1, "%" + target + "%");
					break;
			}
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String tag = rs.getString("Tag");
				
				Entry entry = new Entry(rs.getString("ID"), rs.getString("Abbr"), rs.getString("Chinese"),
						rs.getString("EnglishLiteral"), rs.getString("EnglishFigurative"), rs.getString("Pinyin"),
						rs.getString("Example"), rs.getString("ExampleTranslation"), rs.getString("Origin"),
						rs.getString("OriginTranslation"), rs.getString("Frequency"));
				
				boolean found = false;
				for(int i=0; i<result.size();i++) {
					Entry eachEntry = result.get(i);
					if (eachEntry.getId().equals(rs.getString("ID"))) {
						if(tag != null) {
							eachEntry.addTag(tag);
						}
						found = true;
						break;
					}
				}
				
				if(!found) {
					if(tag != null) {
						entry.addTag(tag);
					}
					result.add(entry);
				}
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < result.size(); i++) {
			Entry eachEntry = result.get(i);
			if (eachEntry.hasTags(tags)) {
				tagResults.add(eachEntry);
			}
		}
		
		return tagResults;
	}
}
