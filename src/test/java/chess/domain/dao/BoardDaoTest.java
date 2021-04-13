package chess.domain.dao;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {
    @Test
    void delete() throws SQLException {
        final BoardDao boardDao = new BoardDao();
        boardDao.delete(1);
    }
}
