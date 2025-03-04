package com.silas.board.persistence.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardEntity {
    private Long id;
    private String name;
    private List<BoardColumnEntity> boardColumnEntityList = new ArrayList<>();
}
