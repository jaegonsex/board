package jg.board.post;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostDto {
    private Long id;
    private String userNickname;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public static PostDto from(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .userNickname(post.getAccount().getNickname())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }

}
