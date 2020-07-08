package com.tasker.server.boards.rest.services;

import com.tasker.server.boards.Board;
import com.tasker.server.boards.rest.dao.BoardDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BoardServiceImpl implements BoardService {

    @Resource
    private BoardDao boardDao;

    @Override
    public void create(Board board) {

        boardDao.create(board);
    }
}
