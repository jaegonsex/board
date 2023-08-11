package jg.board.post;

import jakarta.transaction.Transactional;
import jg.board.user.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostDto createPost(Account account, String title, String content) {
        Post post = Post.builder()
                .title(title)
                .account(account)
                .content(content)
                .build();

        return PostDto.from(postRepository.save(post));
    }

    public PostDto showPost(Long id) {

        return PostDto.from(postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not exist post")));

    }


}
