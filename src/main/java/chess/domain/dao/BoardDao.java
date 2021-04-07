package chess.domain.dao;

import chess.domain.dto.BoardDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public final class BoardDao {

    public Optional<BoardDto> load(final long id) {
        final String query = "SELECT * FROM board WHERE id = ?";

        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query);
            final ResultSet rs = pstmt.executeQuery()) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            if (!rs.next()) {
                return Optional.empty();
            }

            final String team = rs.getString("team");
            final boolean isGameOver = rs.getBoolean("isGameOver");
            return Optional.of(new BoardDto(team, isGameOver));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public BoardDto save(final long id, final String team, final boolean isGameOver) {
        final String query = "INSERT INTO board VALUES (?, ?, ?)";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.setString(2, team);
            pstmt.setBoolean(3, isGameOver);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BoardDto(team, isGameOver);
    }

    public void updateTeam(final long id, final String team) {
        final String query = "UPDATE board SET team = ? WHERE isGameOver = false AND id = ?";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, team);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIsGameOver(final long id) {
        final String query = "UPDATE board SET isGameOver = true WHERE id = ?";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        final String query = "TRUNCATE TABLE board";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
