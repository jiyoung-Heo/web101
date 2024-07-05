package com.book.web101.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.book.web101.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
	// JpaRepository<T,ID>
	// T: 테이블에 매핑될 엔티티 클래스 ID: 엔티티의 기본 키 타입
	
	// 메서드 이름: jpa가 파싱하여 적절한 쿼리를 작성하여 실행
	// 매개변수: 쿼리의 where문에 들어갈 값
	// 복잡한 쿼리의 경우 @Query 어노테이션을 통해 지정할 수 있다. 
	// ?1 는 메서드의 매개변수의 순서 위치
//	@Query("select * from Todo t where t.userId = ?1")
	List<TodoEntity> findByUserId(String userId);
	
	//실습: crud구현하기

}
