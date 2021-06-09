package com.neivor.vertx.Bd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Consulta extends Sentencia {
    private ResultSet resultSet;
    private String sqlConsulta;

    private final List<Object> parametros;

    private final HashMap<String, String> columnas;

    public Consulta() {
        parametros = new ArrayList<>();
        columnas = new HashMap<>();
    }

    public void agregarParametro(Object parametro) {
        parametros.add(parametro);
    }

    /**
     * Obtiene el valor del primer campo del primer registro del resultado de una consulta sql.
     *
     * @param sqlConsulta la consulta sql
     * @return el valor del campo o un <code>String</code> vacio si no encuentra resultado o el valor del campo es
     * <code>null</code>
     * @throws SQLException si se produce un error de tipo sql
     */
    public String obtenerCampo(String sqlConsulta) throws SQLException {
        try {
            this.sqlConsulta = sqlConsulta;
            ejecutarResultSet();
            if (!resultSet.first() || resultSet.getString(1) == null) {
                return "";
            }
            String campo = resultSet.getString(1);
            return campo;
        } finally {
            cerrarResultSet();
        }

    }

    public String[] obtenerRegistro(String sqlConsulta) throws SQLException {
        try {
            this.sqlConsulta = sqlConsulta;
            ejecutarResultSet();
            if (!resultSet.first()) {
                return new String[0];
            }
            int cantidadColumnas = resultSet.getMetaData().getColumnCount();
            String[] datoRegistro = new String[cantidadColumnas];

            for (int i = 0; i < cantidadColumnas; i++) {
                datoRegistro[i] = resultSet.getString(i + 1);
            }
            return datoRegistro;
        } finally {
            cerrarResultSet();
        }

    }

    public void establecerDatosRegistro(String sqlConsulta) throws SQLException {
        try {
            this.sqlConsulta = sqlConsulta;
            ejecutarResultSet();

            if (!resultSet.first()) {
                return;
            }

            int cantidadColumnas = resultSet.getMetaData().getColumnCount();

            String nombreColumna;
            String valorColumna;

            for (int i = 0; i < cantidadColumnas; i++) {
                nombreColumna = resultSet.getMetaData().getColumnName(i + 1);
                valorColumna = resultSet.getString(i + 1);
                columnas.put(nombreColumna, valorColumna);
            }
        } finally {
            cerrarResultSet();
        }
    }

    public String obtenerValorCampo(String nombreCampo) {
        return columnas.get(nombreCampo);
    }

    public String[] obtenerColumna(String sqlConsulta) throws SQLException {
        try {
            this.sqlConsulta = sqlConsulta;
            ejecutarResultSet();
            if (!resultSet.first()) {
                return new String[0];
            }
            resultSet.last();

            int cantidadFilas = resultSet.getRow();
            String[] datoFila = new String[cantidadFilas];
            resultSet.first();

            for (int i = 1; i <= cantidadFilas; i++) {
                datoFila[i - 1] = resultSet.getString(1);
                resultSet.next();
            }
            return datoFila;
        } finally {
            cerrarResultSet();
        }

    }

    public String[][] obtenerRegistros(String sqlConsulta) throws SQLException {
        try {
            this.sqlConsulta = sqlConsulta;
            ejecutarResultSet();
            if (!resultSet.first()) {
                return new String[0][0];
            }
            int cantidadColumnas = resultSet.getMetaData().getColumnCount();

            resultSet.last();
            int cantidadFilas = resultSet.getRow();

            String[][] registros = new String[cantidadFilas][cantidadColumnas];

            resultSet.first();
            for (int i = 1; i <= cantidadFilas; i++) {
                for (int j = 1; j <= cantidadColumnas; j++) {
                    registros[i - 1][j - 1] = resultSet.getString(j);
                }
                resultSet.next();
            }
            return registros;
        } finally {
            cerrarResultSet();
        }

    }

    private void cerrarResultSet() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        cerrarPreparedStatement();
    }

    private void ejecutarResultSet() throws SQLException {
        conectarABd();
        if (sqlConsulta == null) {
            throw new IllegalArgumentException("Error en la consulta, la sentencia sql no debe ser null");
        }
        preparedStatement = getConnection().prepareStatement(sqlConsulta);
        establecerSentenciaSql();
        for (int i = 0; i < parametros.size(); i++) {
            preparedStatement.setObject(i + 1, parametros.get(i));
        }
        resultSet = preparedStatement.executeQuery();
        establecerSentenciaSql();
    }

}
