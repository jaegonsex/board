package jg.board.board;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDto {
    private Long id;
    private String subject;
    private String description;

    public static BoardDto from(Board board){
        return BoardDto.builder()
                .id(board.getId())
                .description(board.getDescription())
                .subject(board.getSubject())
                .build();
    }
}
