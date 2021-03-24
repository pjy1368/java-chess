package chess.domain.piece;

import chess.domain.position.Difference;
import chess.domain.position.Position;
import java.util.List;

public abstract class GeneralPiece extends Piece {

    private final List<Direction> possibleDirections;

    public GeneralPiece(final Team team, final String initialName) {
        super(team, initialName);
        this.possibleDirections = createPossibleDirections();
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece piece) {
        return isPossibleDirection(source, target) && (isOpponent(piece) || piece.isBlank());
    }

    private boolean isPossibleDirection(final Position source, final Position target) {
        final List<Integer> result = target.subtract(source);
        final Difference difference = new Difference(result);
        return possibleDirections.stream()
            .anyMatch(possibleDirection -> possibleDirection.isSameDirection(difference));
    }

    protected abstract List<Direction> createPossibleDirections();

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
