package com.tasker.server.boards.rest;

import com.tasker.server.boards.rest.services.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class BoardCreationController {

    @Resource
    private BoardService boardService;

    @PostMapping("/boards")
    ResponseEntity<?> createBoard(@Valid @RequestBody BoardCreationRequest request) {
        boardService.create(request);

        return ResponseEntity.ok("Board Created");
    }
}
