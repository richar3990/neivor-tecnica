package com.neivor.vertx.Dao;

import com.neivor.vertx.Bd.Abm;
import com.neivor.vertx.Constantes.EstadoOrdenPago;
import com.neivor.vertx.Models.Ordenes_pago;

import java.sql.SQLException;


/**

 * Esta clase define las Altas , Bajas y modificaciones de la tabla Ordenes_pagoDao

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */

/**
 * Guarda un nueva orden de pago.
 */
public class Ordenes_pagoDao {
    public void guardar(Ordenes_pago ordenesPago) throws SQLException {
        Abm abmGuardador = new Abm("ordenes_pago");
        abmGuardador.agregarColumna("idcondomino", ordenesPago.getIdcondominio());
        abmGuardador.agregarColumna("estadoOrdenPago", ordenesPago.getEstadoOrdenPago());
        abmGuardador.agregarColumna("IDSERVICIOS", ordenesPago.getServicios_idservicios());
        abmGuardador.agregarColumna("montoPago", ordenesPago.getMontoPago());
        abmGuardador.ejecutarInsert();

/**
 * Actualiza un nueva orden de pago una vez pagada.
 * @param idOrdenPago define cual orden de pago se debe de actualizar.
 */
    }
    public void actulizarEstado(String idOrdenPago) throws SQLException {
        Abm abmActualizaddor = new Abm("ordenes_pago");
        abmActualizaddor.agregarColumna("ESTADOORDENPAGO", EstadoOrdenPago.PAGADO);
        abmActualizaddor.establecerCondicion("IDORDENES_PAGO="+ idOrdenPago);
        abmActualizaddor.ejecutarUpdate();
    }
}
