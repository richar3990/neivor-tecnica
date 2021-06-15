package com.neivor.vertx.Querys;
import com.neivor.vertx.Bd.Consulta;
import com.neivor.vertx.Constantes.EstadoOrdenPago;
import com.neivor.vertx.Models.Ordenes_pago;

import java.sql.SQLException;


/**

 * Esta clase define las diferentes consultas sql que se realiza a la tabla orden_pago

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */
public class OrdenPagoQuery {
    /**
     * Obtiene un nuevo numero de orden de pago
     * @return numero de orden de pago.
     */
    public String obtenerNumeroOrden() throws SQLException {
        String sql = "SELECT MAX(IDORDENES_PAGO) AS id FROM ordenes_pago";
        Consulta consulta = new Consulta();
        return consulta.obtenerCampo(sql);
    }
    /**
     * Verifica que exista una orden de pago
     * @param idOrden para filtrar si existe el id ingresado
     * @return objeto ordenesdepago con los datos si existe.
     */
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

    }

}
