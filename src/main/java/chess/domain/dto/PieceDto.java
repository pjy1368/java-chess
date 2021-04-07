package chess.domain.dto;

import chess.domain.dto.response.ResponseDto;

public final class PieceDto implements ResponseDto {

    private final long id;
    private final String position;
    private final String name;

    public PieceDto(final String position, final String name) {
        id = 1;
        this.position = position;
        this.name = name;
    }
}
