package com.tasker.server.boards.rest.services;

import com.tasker.server.boards.Board;
import com.tasker.server.boards.rest.dao.BoardDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {

    @Resource
    private BoardDao boardDao;

    @Override
    public void create(Board board) {
        boardDao.create(board);
    }

    @Override
    public void delete(UUID id) {
        boardDao.delete(id);
    }

    @Override
    public void update(Board board) {
        boardDao.update(board);
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
