package com.book.web101.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
//유의미한 데이터를 사용하기 위해 사용하는 디자인패턴
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//이름 지정하지 않으면 클래스 이름이 entity이름
@Table(name="Todo")
//이 엔티티는 데이터베이스의 Todo테이블에 매핑된다는 뜻
//만약 name을 명시하지 않는다면 @Entity의 이름을 테이블 이름으로 간주
public class TodoEntity {
	@Id 
	//기본키가 될 필드에 지정
	@GeneratedValue(generator="system-uuid")
	//자동으로 기본키를 생성하겠다. 매개변수인 
	//generator로 어떤 방식으로 id를 생성할지 지정 할 수 있음
	//system-uuid: @GenericGenerator에서 정의한 generator의 이름
	@GenericGenerator(name="system-uuid", strategy="uuid")
	//hibername가 제공하는 기본 generator가 아닌 나만의 generator를 사용하고 싶을 경우 이용
	//기본 generator: INCREMENTAL, SEQUENCE, IDENTITY
	
	//uuid를 사용하는 system-uuid라는 이름의 genericgenerator를 만들었고 이것을 
	//@GeneratedValue가 참조하여 사용
	private String id;
	
	private String userId;
	private String title;
	private boolean done;
}
