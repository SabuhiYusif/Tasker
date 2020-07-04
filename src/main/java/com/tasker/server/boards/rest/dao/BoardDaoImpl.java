package com.tasker.server.boards.rest.dao;

import com.tasker.server.boards.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class BoardDaoImpl implements BoardDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void create(Board board) {
        jdbcTemplate.update("INSERT INTO tasker.boards(id, title, created_at, description) VALUES (?, ?, ?, ?)",
            board.getId(), board.getTitle(), board.getCreatedAt(), board.getDescription());
    }
}
