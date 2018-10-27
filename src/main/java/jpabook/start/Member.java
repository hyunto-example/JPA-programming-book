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
@Table(name = "MEMBER")
public class Member {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String username;

	// 매핑 정보가 없는 필드
	private Integer age;

}
