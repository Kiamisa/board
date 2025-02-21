--liquibase formatted sql
--changeset silas:202501191223
--comment: board table create

CREATE TABLE BOARDS (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

--rollback DROP TABLE BOARDS
