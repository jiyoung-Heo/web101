package com.book.web101.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.web101.model.TodoEntity;
import com.book.web101.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
	private TodoRepository repository;
	
	@Autowired
	public TodoService(TodoRepository repository) {
		super();
		this.repository = repository;
	}


	public String testService() {
		//TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//repo에 TodoEntity 저장
		repository.save(entity);
		//TodoEntity 검색
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
}
