package jg.board.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardDto create(String subject, String description) {
        checkDuplicateSubjectIsExist(subject);


        return BoardDto.from(boardRepository.save(
                Board.builder()
                        .subject(subject)
                        .description(description)
                        .build()));

    }

    private void checkDuplicateSubjectIsExist(String subject) {
        if (boardRepository.existsBySubject(subject)) {
            throw new RuntimeException("duplicated subject");
        }
    }

    public BoardDto update(Long id, String subject, String description) {
        checkDuplicateSubjectIsExist(subject);
        Board board = getBoard(id);
        board.setSubject(subject);
        board.setDescription(description);
        return BoardDto.from(boardRepository.save(board));
    }

    public List<BoardDto> getBoards() {
        return boardRepository.findAll().stream()
                .map(BoardDto::from).toList();
    }

    public void delete(Long id) {
        Board board = getBoard(id);
        boardRepository.delete(board);
    }

    private Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new RuntimeException("not exist board."));
    }
}
