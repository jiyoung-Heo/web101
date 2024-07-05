package com.book.web101.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.web101.model.TodoEntity;
import com.book.web101.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

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

    public List<TodoEntity> create(final TodoEntity entity) {
//		외부메서드로 분리함
////		validations
//		if(entity == null){
//			log.warn("Entity cannot be null");
//			throw new RuntimeException("Entity cannot be null");
//		}
//
//		if(entity.getUserId() == null) {
//			log.warn("Unknown user");
//			throw new RuntimeException("Unknown user");
//		}
        validate(entity);

        repository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(String userId) {
        return repository.findByUserId(userId);
    }

    public void update(final TodoEntity entity) {

    }

    public List<TodoEntity> delete(final TodoEntity entity) {
		//해당 엔티티가 유효한지 확인
        validate(entity);
        try {
			//엔티티 삭제
            repository.delete(entity);

        } catch (Exception e) {
			//Exception 발생 시 id, exception을 로깅함
            log.error("error deleting entity", entity.getId(), e);
			// 컨트롤러로 exception 보낸다. 데이터베이스 내부 로직을 캡슐화하려면 e를 리턴하지 않고 새 exceptionㅍ오브젝트를 리턴한다.
			throw new RuntimeException("error deleting entity"+entity.getId());
		}
		return retrieve(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
//		validations
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }
}
