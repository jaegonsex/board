package jg.board.user;

import lombok.Builder;
import lombok.Getter;

public class Login {
    @Getter
    static class Request{
        private String email;
        private String password;
    }

    @Getter
    @Builder
    static class Response{
        private String jwt;
    }
}
