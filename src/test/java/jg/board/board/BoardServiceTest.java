package jg.board.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    @InjectMocks
    BoardService boardService;
    @Mock
    BoardRepository boardRepository;

    @DisplayName("게시판 생성 성공 테스트")
    @Test
    void createBoardTest() {
        final String subject = "test";
        final String description = "description";

        when(boardRepository.save(any())).thenReturn(
                Board.builder()
                        .subject(subject)
                        .description(description)
                        .build()
        );
        when(boardRepository.existsBySubject(any())).thenReturn(false);

        BoardDto boardDto = boardService.create(subject, description);

        assertEquals(boardDto.getSubject(), subject);
        assertEquals(boardDto.getDescription(), description);

    }

    @DisplayName("게시판 생성 실패 테스트 - 중복된 게시판 주제")
    @Test
    void createBoardTestFailure_DuplicateSubject() {
        final String subject = "test";
        final String description = "description";

        when(boardRepository.existsBySubject(any())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> boardService.create(subject, description));

    }

    @DisplayName("게시판 수정 성공 테스트")
    @Test
    void updateBoardTest() {
        final String subject = "test";
        final String description = "description";
        Board board = Board.builder().subject(subject)
                .description(description).build();

        when(boardRepository.findById(any())).thenReturn(Optional.of(board));
        when(boardRepository.save(any())).thenReturn(board);
        when(boardRepository.existsBySubject(any())).thenReturn(false);

        final String updateSubject = "updated";
        final String updateDescription = "updatedDescription";
        BoardDto boardDto = boardService.update(1L, updateSubject, updateDescription);

        assertEquals(boardDto.getSubject(), updateSubject);
        assertEquals(boardDto.getDescription(), updateDescription);

    }

    @DisplayName("게시판 수정 실패 테스트 - 중복된 게시판 주제")
    @Test
    void updateBoardTestFailure_DuplicateSubject() {
        final String subject = "test";
        final String description = "description";
        when(boardRepository.existsBySubject(any())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> boardService.update(1L, subject, description));

    }

    @DisplayName("게시판 삭제 테스트")
    @Test
    void deleteBoard() {
        final String subject = "test";
        final String description = "description";
        Board board = Board.builder().subject(subject)
                .description(description).build();

        when(boardRepository.findById(any())).thenReturn(Optional.of(board));

        boardService.delete(1L);
        verify(boardRepository).delete(board);
    }

    @DisplayName("게시판 삭제 테스트 실패 - 존재하지 않는 게시판")
    @Test
    void deleteBoardFailure_NotExistBoard() {
        when(boardRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> boardService.delete(1L));

    }

    @DisplayName("게시판 목록 보기 테스트")
    @Test
    void getBoards() {
        List<Board> boards = List.of(
                new Board("test1", "description1"),
                new Board("test2", "description2"),
                new Board("test3", "description3"),
                new Board("test4", "description4"),
                new Board("test5", "description5")
        );

        when(boardRepository.findAll()).thenReturn(boards);

        List<BoardDto> boardDtoList = boardService.getBoards();

        assertEquals(boardDtoList.size(), boards.size());
    }

}