package com.tasker.server.boards.rest.services;

import com.tasker.server.boards.Board;
import com.tasker.server.boards.rest.BoardCreationRequest;

import java.util.List;
import java.util.UUID;

public interface BoardService {

    void create(BoardCreationRequest request);

    List<Board> fetchAll();

    Board fetch(UUID id);
}
