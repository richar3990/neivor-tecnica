package com.neivor.vertx.Dao;

import com.neivor.vertx.Bd.Abm;
import com.neivor.vertx.Models.Ordenes_pago;

import java.sql.SQLException;

public class Ordenes_pagoDao {
    public void guardar(Ordenes_pago ordenesPago) throws SQLException {
        Abm abmGuardador = new Abm("ordenes_pago");
        abmGuardador.agregarColumna("idcondominio", ordenesPago.getIdcondominio());
        abmGuardador.agregarColumna("estadoOrdenPago", ordenesPago.getEstadoOrdenPago());
        abmGuardador.agregarColumna("servicios_idservicios", ordenesPago.getServicios_idservicios());
        abmGuardador.agregarColumna("montoPago", ordenesPago.getMontoPago());
        abmGuardador.ejecutarInsert();
    }
}
