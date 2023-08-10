package jg.board.user;

import lombok.Builder;
import lombok.Getter;

public class Signup {
    @Getter
    static class Request {
        private String email;
        private String password;
        private String nickname;
    }

    @Getter
    @Builder
    static class Response {
        private String email;
        private String nickname;

        static Response from(AccountDto accountDto) {
            return Response.builder()
                    .email(accountDto.getEmail())
                    .nickname(accountDto.getNickname())
                    .build();
        }
    }

}
