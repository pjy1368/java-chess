package chess.domain.dao;

import chess.domain.dto.BoardDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class BoardDao {

    public BoardDto load() throws SQLException {
        try (final Connection conn = ConnectionSetup.getConnection()) {
            final String query = "SELECT * FROM board";
            final PreparedStatement pstmt = conn.prepareStatement(query);
            final ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            final String team = rs.getString("team");
            final boolean isGameOver = rs.getBoolean("isGameOver");
            return new BoardDto(team, isGameOver);
        }
    }

    public void save(final String team, final boolean isGameOver) throws SQLException {
        try (final Connection conn = ConnectionSetup.getConnection()) {
            final String query = "INSERT INTO board VALUES (?, ?)";
            final PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, team);
            pstmt.setBoolean(2, isGameOver);
            pstmt.executeUpdate();
        }
    }

    public void deleteAll() throws SQLException {
        try (final Connection conn = ConnectionSetup.getConnection()) {
            final String query = "TRUNCATE TABLE board";
            final PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        }
    }
}
