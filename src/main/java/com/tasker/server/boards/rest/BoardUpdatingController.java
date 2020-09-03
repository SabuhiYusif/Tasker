package com.tasker.server.boards.rest;

import com.tasker.server.boards.Board;
import com.tasker.server.boards.rest.services.BoardService;
import com.tasker.server.rest.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
@CrossOrigin
public class BoardUpdatingController {

    @Resource
    private BoardService boardService;

    @PutMapping("/boards/{id}")
    ResponseEntity<String> updateBoard(@PathVariable String id, @Valid @RequestBody BoardUpdatingRequest request) {

        UUID boardId = UUID.fromString(id);
        Board board = boardService.fetch(boardId);
        if (board == null) {
            throw new RecordNotFoundException("Board not found for id - " + id);
        }
        board.setTitle(request.getTitle());
        board.setDescription(request.getDescription());
        boardService.update(board);

        return ResponseEntity.ok("Board Updated");
    }

}
