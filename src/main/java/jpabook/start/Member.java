package jpabook.start;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
public class Member {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String username;

	// 매핑 정보가 없는 필드
	private String age;

}
