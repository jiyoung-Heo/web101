package com.book.web101.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.web101.dto.ResponseDTO;
import com.book.web101.service.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {
	
	private final TodoService service;
	
	@Autowired
	public TodoController(TodoService service) {
		super();
		this.service = service;
	}

	@GetMapping("/test")
	public ResponseEntity<?> testTodo(){
		String str = service.testService();
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	
}
