package com.book.web101.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.web101.dto.ResponseDTO;

@Controller
@RequestMapping("todo")
public class TodoController {
	@GetMapping("getTest")
	public ResponseEntity<?> testTode(){
		List<String> list = new ArrayList<>();
		list.add("test");
		ResponseDTO<String> dto = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(dto);
	}
}
