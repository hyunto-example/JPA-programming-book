import jpabook.start.Board;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.*;

public class BoardTest extends TestConnectionManager {

	@Test
	public void 게시글_추가() {
		// given
		Board board = new Board();
		board.setTitle("title");
		board.setContent("content");

		List<Board> boards = new ArrayList<>();

		// when
		try {
			entityTransaction.begin();
			entityManager.persist(board);
			boards = entityManager.createQuery("select b from Board b", Board.class).getResultList();
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
			assertThat(e.getMessage(), false);
		}

		// then
		assertEquals(1, boards.size());
	}

}
