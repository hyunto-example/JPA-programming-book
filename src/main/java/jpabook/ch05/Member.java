package jpabook.ch05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MEMBER_CH05")
@Table(name = "MEMBER_CH05")
public class Member {

	@Id
	@Column(name = "MEMBER_ID")
	private String id;

	private String username;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	public Member(String id, String username) {
		this.id = id;
		this.username = username;
	}

}
