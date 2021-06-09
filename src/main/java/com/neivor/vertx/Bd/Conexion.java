package com.neivor.vertx.Bd;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {
    Connection connection;

    public Conexion() {
        connection = null;
    }

    public void conectarABd() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://localhost:3306/neivor?allowMultiQueries=true");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("1234");
        connection = mysqlDataSource.getConnection();
    }

    public Connection getConnection(){
        return connection;
    }

    public void desconectarDeBd() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}
