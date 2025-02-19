package com.silas.board;

import com.silas.board.persistence.migration.MigrationStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

import static com.silas.board.persistence.config.ConnectionConfig.getConnection;


@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) throws SQLException {
		try(var connection = getConnection()){
			new MigrationStrategy(connection).migrate();
		}
		//SpringApplication.run(BoardApplication.class, args);

	}

}
