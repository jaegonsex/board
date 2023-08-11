package jg.board.post;

import jakarta.persistence.*;
import jg.board.board.Board;
import jg.board.user.Account;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;


}
