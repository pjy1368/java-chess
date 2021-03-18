package chess.domain.strategy.initializestategy;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Horizontal;
import chess.domain.position.Position;

import java.util.*;
import java.util.stream.Collectors;

public class PawnInitializer implements LocationInitializer {
    private static final List<String> HORIZONTALS = Arrays.stream(Horizontal.values())
            .map(Horizontal::getSymbol)
            .collect(Collectors.toList());
    private static final List<String> VERTICALS_WHITE = Collections.singletonList("2");
    private static final List<String> VERTICALS_BLACK = Collections.singletonList("7");

    @Override
    public Map<Position, Piece> initialize() {
        final Map<Position, Piece> chessBoard = new HashMap<>();
        for (String horizontal : HORIZONTALS) {
            VERTICALS_BLACK.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), new Pawn(true)));
            VERTICALS_WHITE.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), new Pawn(false)));
        }
        return chessBoard;
    }
}