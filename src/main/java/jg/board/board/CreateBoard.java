package jg.board.board;

import lombok.Builder;
import lombok.Getter;

public class CreateBoard {

    @Builder
    @Getter
    public static class Request{
        private String subject;
        private String description;
    }
    @Builder
    public static class Response{
        private String subject;
        private String description;

        public static Response from(BoardDto boardDto){
            return Response.builder()
                    .subject(boardDto.getSubject())
                    .description(boardDto.getDescription())
                    .build();
        }

    }


}
