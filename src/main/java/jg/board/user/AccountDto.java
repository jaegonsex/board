package jg.board.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountDto {
    private String email;
    private String nickname;

    static AccountDto from(Account account){
        return AccountDto.builder()
                .email(account.getEmail())
                .nickname(account.getNickname())
                .build();
    }
}
