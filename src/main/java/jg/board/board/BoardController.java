package jg.board.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<CreateBoard.Response> create(CreateBoard.Request request) {
        return ResponseEntity.ok()
                .body(CreateBoard.Response.from(
                        boardService.create(request.getSubject(), request.getDescription())
                ));

    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardDto>> boardList() {
        return ResponseEntity.ok()
                .body(boardService.getBoards());
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }





}
