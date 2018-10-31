package jpabook.ch05;

import jpabook.TestConnectionManager;
import org.junit.Test;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class ExampleTest extends TestConnectionManager {

	private void prepare() {
		try {
			entityTransaction.begin();

			// 팀1 저장
			Team team1 = new Team("team1", "팀1");
			entityManager.persist(team1);

			// 회원1 저장
			Member member1 = new Member("member1", "회원1");
			member1.setTeam(team1);
			entityManager.persist(member1);


			// 회원2 저장
			Member member2 = new Member("member2", "회원2");
			member2.setTeam(team1);
			entityManager.persist(member2);

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
	}

	@Test
	public void 저장() {
		this.prepare();
	}

	@Test
	public void 조회_객체그래프탐색() {
		this.prepare();

		try {
			entityTransaction.begin();

			Member member = entityManager.find(Member.class, "member1");
			Team team = member.getTeam();
			System.out.println("팀 이름 : " + team.getName());

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
	}

	@Test
	public void 조회_JPQL() {
		this.prepare();

		String jpql = "select m from MEMBER_CH05 m join m.team t where t.name=:teamName";

		try {
			entityTransaction.begin();

			List<Member> resultList = entityManager.createQuery(jpql, Member.class)
					.setParameter("teamName", "팀1")
					.getResultList();
			System.out.println(resultList.toString());
			for (Member m : resultList) {
				System.out.println("[query] member.username=" + m.getUsername());
			}

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
	}

	@Test
	public void 수정() {
		this.prepare();

		try {
			entityTransaction.begin();

			Team team2 = new Team("team2", "팀2");
			entityManager.persist(team2);

			Member member = entityManager.find(Member.class, "member1");

			System.out.println("member1의 이전 팀 : " + member.getTeam().getName());
			member.setTeam(team2);
			System.out.println("member1의 현재 팀 : " + member.getTeam().getName());

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
	}

	@Test
	public void 연관관계_제거() {
		this.prepare();

		try {
			entityTransaction.begin();

			Member member1 = entityManager.find(Member.class, "member1");
			member1.setTeam(null);

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
	}

	@Test
	public void 연관된_엔티티_삭제() {
		this.prepare();

		try {
			entityTransaction.begin();

			Member member1 = entityManager.find(Member.class, "member1");
			Member member2 = entityManager.find(Member.class, "member2");

			member1.setTeam(null);
			member2.setTeam(null);

			Team team1 = entityManager.find(Team.class, "team1");
			entityManager.remove(team1);

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}

		try {
			entityTransaction.begin();

			Team team1 = entityManager.find(Team.class, "team1");
			assertNull(team1);

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
	}

}
