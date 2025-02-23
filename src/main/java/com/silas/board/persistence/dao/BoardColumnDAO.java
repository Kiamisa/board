package com.silas.board.persistence.dao;


import com.silas.board.persistence.entity.BoardColumnEntity;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class BoardColumnDAO {
    private final Connection connection;

    public BoardColumnEntity insert (final BoardColumnEntity entity) throws SQLException {
        var sql = "INSERT INTO BOARDS_COLUMNS (name, `order`, kind, board_id) VALUES (?,?,?,?)";
        try(var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            var i = 1;
            statement.setString(i++, entity.getName());
            statement.setInt(i++, entity.getOrder());
            statement.setString(i++, entity.getKind().name());
            statement.setLong(i++, entity.getBoard().getId());
            statement.executeUpdate();

            try (var generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    entity.setId(generatedKeys.getLong(1));
                }
            }
            return entity;
        }
    }

    public List<BoardColumnEntity> findByBoardId(Long id) throws SQLException {
        return null;
    }
}
