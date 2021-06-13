package com.neivor.vertx.Querys;
import com.neivor.vertx.Bd.Consulta;
import com.neivor.vertx.Constantes.EstadoOrdenPago;
import com.neivor.vertx.Models.Ordenes_pago;

import java.sql.SQLException;


public class OrdenPagoQuery {

    public String obtenerNumeroOrden() throws SQLException {
        String sql = "SELECT MAX(IDORDENES_PAGO) AS id FROM ordenes_pago";
        Consulta consulta = new Consulta();
        return consulta.obtenerCampo(sql);
    }
    public Ordenes_pago existeOrden(String idOrden) throws SQLException{
        String sql = "SELECT `ordenes_pago`.`IDORDENES_PAGO`, `ordenes_pago`.`IDCONDOMINO`, " +
                "`ordenes_pago`.`IDSERVICIOS`, `ordenes_pago`.`ESTADOORDENPAGO`, `ordenes_pago`.`MONTOPAGO`  " +
                "FROM `ordenes_pago`  WHERE `ordenes_pago`.`IDORDENES_PAGO` = ? AND " +
                "`ordenes_pago`.`ESTADOORDENPAGO` <> '"+EstadoOrdenPago.PAGADO+"'";
        Consulta consulta = new Consulta();
        consulta.agregarParametro(idOrden);
        consulta.establecerDatosRegistro(sql);
        Ordenes_pago ordenesPago = new Ordenes_pago();
        ordenesPago.setIdordenes_pago(consulta.obtenerValorCampo("IDORDENES_PAGO"));
        ordenesPago.setIdcondominio(consulta.obtenerValorCampo("IDCONDOMINO"));
        ordenesPago.setServicios_idservicios(consulta.obtenerValorCampo("IDSERVICIOS"));
        ordenesPago.setEstadoOrdenPago(consulta.obtenerValorCampo("ESTADOORDENPAGO"));
        ordenesPago.setMontoPago(consulta.obtenerValorCampo("MONTOPAGO"));

        return ordenesPago;

//        String resultado = consulta.obtenerCampo(sql);
//        resultado = (!resultado.equals("")) ? resultado : "0";
//        int id = Integer.parseInt(resultado);
//        System.out.println(Math.max(id, 0));
//        return Math.max(id, 0);
    }

}
