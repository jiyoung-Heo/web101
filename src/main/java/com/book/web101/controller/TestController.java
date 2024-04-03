package com.book.web101.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.web101.dto.ResponseDTO;
import com.book.web101.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test") // 리소스
public class TestController {
	@GetMapping
	public String testController() {
		return "Hello World!";
	}

	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "Hello World! testGetMapping";
	}

	@GetMapping("/{id}")
	public String testControllerWithPathVariables(@PathVariable(name = "id", required = false) int id) {
		return "hello world! Id " + id;
	}

	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(@RequestParam(name = "id", required = false) int id) {
		return "hello world! Id " + id;
	}

	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDto) {
		return "hello world! Id " + testRequestBodyDto.getId() + " message: " + testRequestBodyDto.getMessage();
	}

	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<>();
		list.add("helloworld! im responseDTO");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return response;
	}
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		List<String> list = new ArrayList<>();
		list.add("helloworld! im responseEntity. and you got 400!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.badRequest().body(response);
	}
}
