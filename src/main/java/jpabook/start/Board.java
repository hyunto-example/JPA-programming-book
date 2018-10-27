package jpabook.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOARD")
//@SequenceGenerator(name = "BOARD_SEQ_GENERATOR", sequenceName = "BOARD_SEQ", initialValue = 1, allocationSize = 1)
//@TableGenerator(name = "BOARD_SEQ_GENERATOR", table = "MY_SEQUENCES", pkColumnValue = "BOARD_SEQ", allocationSize = 1, pkColumnName = "sequence_name", valueColumnName = "next_val")
public class Board {

	/**
	 * H2 Database 에서
	 * @GeneratedValue 전략을 IDENTITY 로 설정할 경우, 테이블 생성시 id 컬럼이 아래와 같이 생성된다.
	 *   - id bigint generated by default as identity
	 *
	 * @GeneratedValue 전략을 SEQUENCE 로 설정할 경우 id 컬럼은 평범하게 생성되지만, 시퀀스가 추가된다.
	 * 시퀀스 이름은 @SequenceGenerator 설정에 따라 달라진다.
	 *   - id bigint not null
	 *   - create sequence hibernate_sequence start with 1 increment by 1
	 *   - call next value for hibernate_sequence
	 *
	 * IDENTITY 전략은 엔티티를 먼저 데이터베이스에 저장한 후, 데이터베이스로 부터 식별자를 조회해서 엔티티에 식별자를 할당한다.
	 * SEQUENCE 전략은 데이터베이스로 부터 식별자를 조회한 후, 조회산 식별자를 엔티티에 할당하고 나서 영속성 컨텍스트에 저장한다. 데이터베이스 저장은
	 * 트랜잭션 커밋시 Flush가 일어날 때 한다.
	 */

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BOARD_SEQ_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

}