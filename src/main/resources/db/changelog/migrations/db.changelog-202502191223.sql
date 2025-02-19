--liquibase formatted sql

--changeset silas:001
--comment: board table create
USE board;

CREATE TABLE BOARDS (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

--rollback DROP TABLE BOARDS;
