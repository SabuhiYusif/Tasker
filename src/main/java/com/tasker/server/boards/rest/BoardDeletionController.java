package com.tasker.server.boards.rest;

import com.tasker.server.boards.Board;
import com.tasker.server.boards.rest.services.BoardService;
import com.tasker.server.rest.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
public class BoardDeletionController {

    @Resource
    BoardService boardService;

    @DeleteMapping("/boards/{id}")
    ResponseEntity<?> delete(@PathVariable String id) {

        UUID boardId = UUID.fromString(id);
        Board board = boardService.fetch(boardId);
        if (board == null) {
            throw new RecordNotFoundException("Board not found for id - " + id);
        }
        boardService.delete(boardId);
        return ResponseEntity.ok("Board Deleted");
    }
}
