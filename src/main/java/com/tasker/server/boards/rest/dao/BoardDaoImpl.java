package com.tasker.server.boards.rest.dao;

import com.tasker.server.boards.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Transactional
    @Override
    public List<Board> fetchBoards() {

        String sql = "SELECT id, title, description, created_at FROM tasker.boards";
        return  jdbcTemplate.query(sql, new BoardRowMapper());
    }

    @Override
    public Board fetchBoard(UUID boardId) {

        String sql = "SELECT id, title, description, created_at FROM tasker.boards WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {boardId}, new BoardRowMapper());
    }

    private static final class BoardRowMapper implements RowMapper<Board> {


        @Override
        public Board mapRow(ResultSet rs, int rowNum) throws SQLException {

            Board board = new Board();
            board.setId((UUID) rs.getObject("id"));
            board.setTitle(rs.getString("title"));
            board.setDescription(rs.getString("description"));
            board.setCreatedAt(rs.getTimestamp("created_at"));

            return board;
        }
    }
}
