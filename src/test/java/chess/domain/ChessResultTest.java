package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChessResultTest {
    private Board board;
    private ChessResult result;

    @BeforeEach
    void setUp() {
        board = new Board();
        result = new ChessResult(board);
    }

    @Test
    @DisplayName("현재 점수 확인하는 기능")
    void checkScore() {
        final Team team = Team.WHITE;
        assertThat(result.calculateScore(team)).isEqualTo(38);

        board.unwrap().put(new Position("a", "3"), new Pawn(Team.WHITE));
        assertThat(result.calculateScore(team)).isEqualTo(38);
        OutputView.printCurrentBoard(board.unwrap());
    }

    @Test
    @DisplayName("우승팀 확인 기능")
    void checkWinner() {
        assertThat(result.getWinner()).isEqualTo(Team.NOTHING);
        board.unwrap().put(new Position("a", "3"), new Rook(Team.WHITE));
        assertThat(result.getWinner()).isEqualTo(Team.WHITE);
        board.unwrap().put(new Position("a", "4"), new Queen(Team.BLACK));
        assertThat(result.getWinner()).isEqualTo(Team.BLACK);
    }
}