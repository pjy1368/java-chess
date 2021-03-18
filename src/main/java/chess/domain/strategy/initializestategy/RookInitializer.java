package chess.domain.strategy.initializestategy;

import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;

import java.util.*;

public class RookInitializer implements LocationInitializer {
    private static final List<String> HORIZONTALS = Arrays.asList("a", "h");
    private static final List<String> VERTICALS_WHITE = Collections.singletonList("1");
    private static final List<String> VERTICALS_BLACK = Collections.singletonList("8");

    @Override
    public Map<Position, Piece> initialize() {
        final Map<Position, Piece> chessBoard = new HashMap<>();
        for (String horizontal : HORIZONTALS) {
            VERTICALS_BLACK.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), new Rook(true)));
            VERTICALS_WHITE.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), new Rook(false)));
        }
        return chessBoard;
    }
}