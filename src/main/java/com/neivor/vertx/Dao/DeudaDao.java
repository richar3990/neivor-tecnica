package com.neivor.vertx.Dao;

import com.neivor.vertx.Bd.Abm;

import java.sql.SQLException;

public class DeudaDao {
    public void actulizarDeuda(String inmporteDeuda, String idDeuda) throws SQLException {
        Abm abmActualizaddor = new Abm("deuda_condomino");
            abmActualizaddor.agregarColumna("IMPORTEADEUDADO", inmporteDeuda);
            abmActualizaddor.establecerCondicion("IDDEUDA_CONDOMINO="+ idDeuda);
            abmActualizaddor.ejecutarUpdate();
        }
    }
