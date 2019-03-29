package com.colewe.ws1819.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.colewe.ws1819.shared.Entry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DownloadServlet extends HttpServlet {
	
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/plain; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.addHeader("Content-Disposition", "attachment;filename=\"chengyu.xml\"");
		
		String target = req.getParameter("target");
		int mode = Integer.parseInt(req.getParameter("mode"));
		
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
					stmt = conn.prepareStatement("select * from Chengyu as c "
							+ "left join ChengyuTag as ct "
							+ "on ct.ChengyuID = c.ID "
							+ "left join Tags as t "
							+ "on t.ID = ct.TagID "
							+ "where EnglishLiteral like ?");
					stmt.setString(1, "%" + target + "%");
					break;
				default:
					stmt = conn.prepareStatement("select * from Chengyu as c "
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
						rs.getString("ChineseExplanation"), rs.getString("EnglishLiteral"), rs.getString("EnglishFigurative"), 
						rs.getString("Pinyin"), rs.getString("Example"), rs.getString("ExampleTranslation"), rs.getString("Origin"),
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
		
		
		resp.getWriter().write(toXML(result));
	}
	
	private String toXML(ArrayList<Entry> results) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
		sb.append("<Entries>");
		for(Entry e: results) {
			sb.append("<Entry>");
			
			sb.append("<ID>" + e.getId() + "</ID>");
			sb.append("<Chinese>" + e.getChinese() + "</Chinese>");
			sb.append("<Pinyin>" + e.getPinyin() + "</Pinyin>");
			sb.append("<Abbr>" + e.getAbbr() + "</Abbr>");
			sb.append("<ChineseExplanation>" + e.getChineseExplanation() + "</ChineseExplanation>");
			sb.append("<EnglishFigurative>" + e.getEnglishFigurative() + "</EnglishFigurative>");
			sb.append("<EnglishLiteral>" + e.getEnglishLiteral() + "</EnglishLiteral>");
			sb.append("<Example>" + e.getExample() + "</Example>");
			sb.append("<ExampleTranslation>" + e.getExampleTranslation() + "</ExampleTranslation>");
			sb.append("<Origin>" + e.getOrigin() + "</Origin>");
			sb.append("<OrignTranslation>" + e.getOrignTranslation() + "</OrignTranslation>");
			sb.append("<tags>");
			for(String tag: e.getTags()) {
				sb.append("<tag>" + tag + "</tag>");
			}
			sb.append("</tags>");
			sb.append("</Entry>");
		}
		
		
		sb.append("</Entries>");
		return sb.toString();
	}
	
}
