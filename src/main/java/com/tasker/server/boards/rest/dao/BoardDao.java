package com.tasker.server.boards.rest.dao;

import com.tasker.server.boards.Board;

import java.util.List;
import java.util.UUID;

public interface BoardDao {
    void create(Board board);

    List<Board> fetchBoards();

    Board fetchBoard(UUID boardId);
}
