package chess.domain.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public final class PieceDao {

    public Optional<Map<Position, Piece>> load() {
        final String query = "SELECT * FROM pieces";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query);
            final ResultSet rs = pstmt.executeQuery()) {
            final Map<Position, Piece> pieces = new TreeMap<>();
            while (rs.next()) {
                final String positionValue = rs.getString("position");
                final Position position = Position.from(positionValue);
                final String name = rs.getString("name");
                pieces.put(position, PieceFactory.correctPiece(name));
            }
            return Optional.of(pieces);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Map<Position, Piece> save(final Map<Position, Piece> pieces) {
        for (final Position position : pieces.keySet()) {
            savePiece(position, pieces.get(position));
        }
        return pieces;
    }

    public void savePiece(final Position position, final Piece piece) {
        final String query = "INSERT INTO pieces VALUES (?, ?)";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, position.horizontalSymbol() + position.verticalSymbol());
            pstmt.setString(2, piece.name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePiece(final Position position, final String name) {
        final String query = "UPDATE pieces SET name = ? WHERE position = ?";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, position.horizontalSymbol() + position.verticalSymbol());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        final String query = "TRUNCATE TABLE pieces";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
