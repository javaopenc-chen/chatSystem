package com.example.demo.chat;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.ChatDao;
import com.example.demo.entity.EntChat;

@Controller
public class ChatController {

	private final ChatDao chatdao;

	public ChatController(ChatDao chatdao) {
		this.chatdao = chatdao;
	}

	@RequestMapping("/")
	public String index(Model model) {
		System.out.println("index");
		model.addAttribute("title", "ようこそ");
		return "index";
	}

	@RequestMapping("/view")
	public String chatRoom(Model model) {
		model.addAttribute("title", "チャットルーム");
		List<EntChat> list = chatdao.searchDb();
		model.addAttribute("dblist", list);
		return "view";
	}

	@RequestMapping("/complete")
	public String complete(Model model, Chat chat) {
		model.addAttribute("title", "チャットルーム");
		EntChat entchat = new EntChat();
		entchat.setName(chat.getName());
		entchat.setComment(chat.getComment());
		chatdao.insertDb(entchat);

		return "redirect:/view";
	}

}
