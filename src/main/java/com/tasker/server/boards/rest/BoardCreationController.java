package com.tasker.server.boards.rest;

import com.tasker.server.boards.Board;
import com.tasker.server.boards.rest.services.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
@CrossOrigin
public class BoardCreationController {

    @Resource
    private BoardService boardService;

    @PostMapping("/boards")
    ResponseEntity<BoardCreatedResponse> createBoard(@Valid @RequestBody BoardCreationRequest request) {

        Board board = new Board();

        board.setId(UUID.randomUUID());
        board.setCreatedAt(new Date());
        board.setTitle(request.getTitle());
        board.setDescription(request.getDescription());

        boardService.create(board);

        return new ResponseEntity<>(new BoardCreatedResponse(board.getId().toString()), HttpStatus.CREATED);
    }

    private class BoardCreatedResponse {
        private String id;

        public BoardCreatedResponse(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
