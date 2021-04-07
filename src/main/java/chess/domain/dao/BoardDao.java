package chess.domain.dao;

import chess.domain.dto.BoardDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public final class BoardDao {

    public Optional<BoardDto> load() {
        final String query = "SELECT * FROM board";

        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query);
            final ResultSet rs = pstmt.executeQuery()) {

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

    public BoardDto save(final String team, final boolean isGameOver) {
        final String query = "INSERT INTO board VALUES (?, ?)";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, team);
            pstmt.setBoolean(2, isGameOver);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BoardDto(team, isGameOver);
    }

    public void updateTeam(final String team) {
        final String query = "UPDATE board SET team = ? WHERE isGameOver = false";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, team);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIsGameOver() {
        final String query = "UPDATE board SET isGameOver = true";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
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
