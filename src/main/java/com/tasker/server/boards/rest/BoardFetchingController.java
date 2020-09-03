package com.tasker.server.boards.rest;

import com.tasker.server.boards.Board;
import com.tasker.server.boards.rest.services.BoardService;
import com.tasker.server.rest.RecordNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class BoardFetchingController {

    @Resource
    BoardService boardService;

    @GetMapping("/boards")
    List<Board> fetchAll() {
        return boardService.fetchAll();
    }

    @GetMapping("/boards/{id}")
    Board fetch(@PathVariable String id) {

        UUID boardId = UUID.fromString(id);
        Board board = boardService.fetch(boardId);

        if (board == null) {
            throw new RecordNotFoundException("Board not found for id - " + id);
        }
        return boardService.fetch(boardId);
    }

}
