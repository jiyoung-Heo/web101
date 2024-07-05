package com.book.web101.controller;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.book.web101.dto.TodoDTO;
import com.book.web101.model.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.book.web101.dto.ResponseDTO;
import com.book.web101.service.TodoService;

@RestController
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

	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "temp-user";

			// dto를 entity로 변환한다.
			TodoEntity entity = TodoDTO.toEntity(dto);

			//id null로 초기화
			entity.setId(null);

			//임시 사용자 아이디를 설정한다. 나중에 spring security 를 통해 수정할 예정
            entity.setUserId(temporaryUserId);

			//TodoEntity 생성
            List<TodoEntity> entities = service.create(entity);

			//자바 스트림을 이용하여 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			//변환된 TodoDTO 리스트를 이용하여 ResponseDTO를 초기화한다.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			//예외 발생 시 error에 메세지를 넣어 리턴
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(e.getMessage()).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping
	public ResponseEntity<?> retrieveTodoList(){
		String temporaryUserId = "temp-user";

		List<TodoEntity> entity = service.retrieve(temporaryUserId);

		// 서비스 메서드의 retrieve()메서드를 사용해 Todo리스트를 가져온다.
		List<TodoDTO> dtos = entity.stream().map(TodoDTO::new).collect(Collectors.toList());

		//자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

		//responseEntity 리턴
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){
		String temporaryUserId = "temp-user";
		//dto를 entity로 변환한다.
		TodoEntity entity = TodoDTO.toEntity(dto);
		//임시 사용자 아이디 설정하기
		entity.setUserId(temporaryUserId);
		//삭제
		List<TodoEntity> entities = service.delete(entity);
		//eneity를 dto list로 변환
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		//변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

		//responseDTO 리턴하기
		return ResponseEntity.badRequest().body(response);
	}
	
}
