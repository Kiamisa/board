package com.silas.board.persistence.dao;

import com.silas.board.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BoardDAO {
    private final Connection connection;

    public BoardEntity insert (final BoardEntity entity) throws SQLException {
        var sql = "INSERT INTO BOARDS (name) VALUES (?)";

        try(var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                }
            }
            return entity;
        }
    }

    public void delete (final Long id) throws SQLException {
        var sql = "DELETE FROM BOARD WHERE id = ?";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public Optional<BoardEntity> findByID (final Long id) throws SQLException {
        var sql = "SELECT id, name FROM BOARD WHERE id = ?";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            if(resultSet.next()){
                var Entity = new BoardEntity();
                Entity.setId(resultSet.getLong("id"));
                Entity.setName(resultSet.getString("name"));
                return Optional.of(Entity);
            }
            return Optional.empty();
        }
    }

    public List<BoardEntity> findAll() throws SQLException {
        var sql = "SELECT id, name FROM BOARD";
        List<BoardEntity> boards = new ArrayList<>();
        try(var statement = connection.prepareStatement(sql);var ResultSet = statement.executeQuery()){
            while (ResultSet.next()) {
                var Entity = new BoardEntity();
                Entity.setId(ResultSet.getLong("id"));
                Entity.setName(ResultSet.getString("name"));
                boards.add(Entity);
            }
        }
        return boards;
    }

    public boolean exists (final Long id) throws SQLException {
        var sql = "SELECT 1 FROM BOARD WHERE id = ?";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            return statement.getResultSet().next();
        }
    }

}
