package com.tasker.server.boards.rest.services;

import com.tasker.server.boards.rest.BoardCreationRequest;

public interface BoardService {

    void create(BoardCreationRequest request);
}
