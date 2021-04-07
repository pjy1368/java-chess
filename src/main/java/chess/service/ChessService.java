package chess.service;

import chess.domain.ChessResult;
import chess.domain.board.Board;
import chess.domain.dao.BoardDao;
import chess.domain.dao.PieceDao;
import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.request.MoveRequest;
import chess.domain.dto.response.BoardAndPieceDto;
import chess.domain.dto.response.Response;
import chess.domain.dto.response.ResponseCode;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ChessService {

    private final PieceDao pieceDao;
    private final BoardDao boardDao;
    private ChessGame chessGame;

    public ChessService() {
        pieceDao = new PieceDao();
        boardDao = new BoardDao();
    }

    public void chessBoardInit() {
        final Map<Position, Piece> chessBoard = pieceDao.load()
            .orElseGet(() -> pieceDao.save(new Board().unwrap()));

        final BoardDto boardDto = boardDao.load()
            .orElseGet(() -> boardDao.save(Team.WHITE.teamName(), false));

        chessGame = new ChessGame(new Board(chessBoard), boardDto.team(), boardDto.isGameOver());
    }

    public Response move(final MoveRequest moveRequest) {
        try {
            final Position source = Position.from(moveRequest.source());
            final Position target = Position.from(moveRequest.target());
            chessGame.move(source, target);
            changeStatusSaveData(source, target);
            return new Response(ResponseCode.SUCCESS.code(), ResponseCode.SUCCESS.message());
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            return new Response(ResponseCode.ERROR.code(), e.getMessage());
        }
    }

    private void changeStatusSaveData(final Position source, final Position target) {
        if (chessGame.isKingDead()) {
            chessGame.changeGameOver();
        }
        chessGame.nextTurn();

        final Map<Position, Piece> chessBoard = chessGame.board().unwrap();
        pieceDao.updatePiece(source, ".");
        pieceDao.updatePiece(target, chessBoard.get(target).name());
        boardDao.updateTeam(chessGame.nowTurn().teamName());
    }

    public Response end() {
        if (chessGame.isGameOver()) {
            boardDao.updateIsGameOver();
            return new Response(ResponseCode.GAME_OVER.code(), ResponseCode.GAME_OVER.message());
        }
        return new Response(ResponseCode.RUN.code(), ResponseCode.RUN.message());
    }

    public ChessResult chessResult() {
        return new ChessResult(chessGame.board());
    }

    public boolean isGameOver() {
        return chessGame.isGameOver();
    }

    public void restart() {
        chessGame = new ChessGame();
        pieceDao.deleteAll();
        boardDao.deleteAll();
    }

    public Response start() {
        final BoardDto boardDto = new BoardDto(chessGame.nowTurn().teamName(), chessGame.isGameOver());
        final Map<Position, Piece> chessBoard = chessGame.board().unwrap();
        final List<PieceDto> pieceDtos = new ArrayList<>();
        chessBoard.forEach((position, piece) -> {
            final String positionValue = position.horizontalSymbol() + position.verticalSymbol();
            pieceDtos.add(new PieceDto(positionValue, imgFileName(piece)));
        });
        return new Response(ResponseCode.SUCCESS.code(), ResponseCode.SUCCESS.message(),
            new BoardAndPieceDto(boardDto, pieceDtos));
    }

    public String imgFileName(final Piece piece) {
        if (piece.isBlank()) {
            return "";
        }
        if (piece.team() == Team.BLACK) {
            return Team.BLACK.teamName().charAt(0) + piece.name();
        }
        return Team.WHITE.teamName().charAt(0) + piece.name().toUpperCase();
    }

}
