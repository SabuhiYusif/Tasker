package com.tasker.server.boards.rest.services;

import com.tasker.server.boards.Board;
import com.tasker.server.boards.rest.BoardCreationRequest;
import com.tasker.server.boards.rest.dao.BoardDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {

    @Resource
    private BoardDao boardDao;

    @Override
    public void create(BoardCreationRequest request) {
        Board board = new Board();

        board.setId(UUID.randomUUID());
        board.setCreatedAt(new Date());
        board.setTitle(request.getTitle());
        board.setDescription(request.getDescription());

        boardDao.create(board);
    }

    @Override
    public List<Board> fetchAll() {
        return boardDao.fetchAll();
    }

    @Override
    public Board fetch(UUID id) {
        return boardDao.fetch(id);
    }

}
