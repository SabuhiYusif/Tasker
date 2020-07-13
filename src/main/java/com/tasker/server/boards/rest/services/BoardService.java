package com.tasker.server.boards.rest.services;

import com.tasker.server.boards.Board;
import java.util.List;
import java.util.UUID;

public interface BoardService {

    List<Board> fetchAll();

    Board fetch(UUID id);

    void create(Board board);
}
