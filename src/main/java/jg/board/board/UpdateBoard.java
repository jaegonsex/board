package jg.board.board;

import lombok.Builder;

public class UpdateBoard {
    @Builder
    static class Request {
        private String subject;
        private String description;
    }

    @Builder
    static class Response {
        private String subject;
        private String description;

        public static Response from(BoardDto boardDto) {
            return Response.builder()
                    .subject(boardDto.getSubject())
                    .description(boardDto.getDescription())
                    .build();
        }
    }
}
