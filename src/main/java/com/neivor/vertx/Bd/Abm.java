package com.neivor.vertx.Bd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Abm extends Sentencia{
    private static final String SQL_INSERT = "INSERT INTO %s (%s) VALUES (%s)";
    private static final String SQL_UPDATE = "UPDATE %s SET %s WHERE %s";
    private static final String SQL_DELETE = "DELETE FROM %s WHERE %s";

    private final String nombreTabla;
    private final List<String> nombresColumnas;
    private final List<Object> valoresColumnas;
    private String condicion;
    private Long generatedKey;

    public Abm(String nombreTabla) {
        this.nombreTabla = nombreTabla;
        nombresColumnas = new ArrayList<>();
        valoresColumnas = new ArrayList<>();
        generatedKey = 0L;
    }

    public void agregarColumna(String nombreColumna, Object valorColumna) {
        nombresColumnas.add(nombreColumna);
        valoresColumnas.add(valorColumna);
    }

    public void establecerCondicion(String condicion) {
        this.condicion = condicion;
    }

    public void ejecutarInsert() throws SQLException {

        try {
            if (nombreTabla == null) {
                throw new IllegalArgumentException("Error al guardar, el nombre de la tabla no debe ser null");
            }
            if (nombresColumnas.isEmpty()) {
                throw new NullPointerException("Error al insertar, faltan los parámetros");
            }
            String columnasConcatenadas = nombresColumnas.toString().replace("[", "").replace("]", "");
            List<String> incognitas = Collections.nCopies(valoresColumnas.size(), "?");
            String valoresConcatenados = incognitas.toString().replace("[", "").replace("]", "");

            conectarABd();
            preparedStatement = connection.prepareStatement(String.format(SQL_INSERT, nombreTabla, columnasConcatenadas, valoresConcatenados),
                    Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < nombresColumnas.size(); i++) {
                preparedStatement.setObject(i + 1, valoresColumnas.get(i));
            }
            ejecutarSentencia();

            try (ResultSet rsGeneratedKey = preparedStatement.getGeneratedKeys()) {
                if (rsGeneratedKey.next()) {
                    generatedKey = rsGeneratedKey.getLong(1);
                }
            }

        } finally {
            cerrarPreparedStatement();
        }
    }

    public void ejecutarUpdate() throws SQLException {
        try {
            if (nombreTabla == null) {
                throw new IllegalArgumentException("Error al actualizar, el nombre de la tabla no debe ser null");
            }
            if (nombresColumnas.isEmpty()) {
                throw new NullPointerException("Error al actualizar, faltan los parámetros");
            }
            if (condicion == null) {
                throw new NullPointerException("Error al actualizar, la condicion no debe ser null");
            }
            List<String> columnas = new ArrayList<>();
            for (String nombresColumna : nombresColumnas) {
                columnas.add(nombresColumna + "=?");
            }
            String columnasConcatenadas = columnas.toString().replace("[", "").replace("]", "");

            conectarABd();
            preparedStatement = getConnection().prepareStatement(String.format(SQL_UPDATE, nombreTabla, columnasConcatenadas, condicion));
            for (int i = 0; i < nombresColumnas.size(); i++) {
                preparedStatement.setObject(i + 1, valoresColumnas.get(i));
            }
            ejecutarSentencia();
        } finally {
            cerrarPreparedStatement();
        }

    }

    public void ejecutarUpdate(String sql) throws SQLException {
        try {
            conectarABd();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < nombresColumnas.size(); i++) {
                preparedStatement.setObject(i + 1, valoresColumnas.get(i));
            }
            ejecutarSentencia();
        } finally {
            cerrarPreparedStatement();
        }
    }

    public void ejecutarDelete() throws SQLException {
        try {
            if (nombreTabla == null) {
                throw new IllegalArgumentException("Error al eliminar, el nombre de la tabla no debe ser null");
            }
            if (condicion == null) {
                throw new NullPointerException("Error al eliminar, la condicion no debe ser null");
            }
            conectarABd();
            preparedStatement = connection.prepareStatement(String.format(SQL_DELETE, nombreTabla, condicion));
            ejecutarSentencia();

        } finally {
            cerrarPreparedStatement();
        }

    }

    public Long getGeneratedKey() {
        return generatedKey;
    }
}
