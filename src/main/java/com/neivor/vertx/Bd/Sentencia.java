package com.neivor.vertx.Bd;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sentencia extends Conexion{
    PreparedStatement preparedStatement;
    private String sentenciaSql;

    Sentencia(){
        preparedStatement = null;
    }

    void ejecutarSentencia() throws SQLException {
        preparedStatement.execute();
        establecerSentenciaSql();
    }

    void cerrarPreparedStatement() throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        desconectarDeBd();
    }

    protected void establecerSentenciaSql() {
        sentenciaSql = preparedStatement.toString().substring(preparedStatement.toString().indexOf(" ") + 1);
    }

    public String obtenerSentenciaSql() {
        return sentenciaSql;
    }

}
