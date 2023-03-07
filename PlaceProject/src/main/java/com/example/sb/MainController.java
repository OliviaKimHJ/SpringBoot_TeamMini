package com.example.sb;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sb.board.BoardEntity;
import com.example.sb.board.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	/*
	 * BoardService를 가져와서 사용할 것이므로 의존성 주입을 해야한다.
	 * 스프링프레임워크의 bean을 찾아주는  Autowired
	 */
	@Autowired 
	private BoardService boardService;
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<BoardEntity> list = boardService.getHiVcnList();
		// System.out.println(list);
		model.addAttribute("list", list);
		
		
		return "index";
	}
}
