package chess.domain.dao;

import chess.domain.board.Board;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    @Test
    void delete() throws SQLException {
        final PieceDao pieceDao = new PieceDao();
        pieceDao.delete();
    }

    @Test
    void save() throws SQLException {
        final PieceDao pieceDao = new PieceDao();
        pieceDao.save(new Board().unwrap());
    }
}