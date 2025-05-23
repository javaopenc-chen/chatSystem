package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntChat;

@Repository
public class ChatDao {
	private final JdbcTemplate db;
	public ChatDao(JdbcTemplate db) {
		this.db = db;
	}
	
	public void insertDb(EntChat entchat) {
		db.update("INSERT INTO chat(name,comment)VALUES(?,?)",entchat.getName(),entchat.getComment());
	}
	
	public List<EntChat>searchDb(){
		String sql ="SELECT * FROM chat";
		List<Map<String,Object>>resultDb1 = db.queryForList(sql);
		List<EntChat>resultDb2 = new ArrayList<EntChat>();
		for(Map<String,Object>result1:resultDb1) {
			EntChat entchatdb = new EntChat();
			//entchatdb.setId((int)result1.get("id"));
			entchatdb.setName((String)result1.get("name"));
			entchatdb.setComment((String)result1.get("comment"));
			resultDb2.add(entchatdb);
		}
		return resultDb2;
	}
}
