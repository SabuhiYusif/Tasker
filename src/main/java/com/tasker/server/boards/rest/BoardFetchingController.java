package com.tasker.server.boards.rest;

import com.tasker.server.boards.Board;
import com.tasker.server.boards.rest.services.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class BoardFetchingController {

    @Resource
    BoardService boardService;

    @GetMapping("/boards")
    List<Board> fetchBoards() {
        return boardService.fetchBoards();
    }

    @GetMapping("/boards/{boardId}")
    Board fetchBoard(@PathVariable UUID boardId) {

        return boardService.fetchBoard(boardId);
    }

}
