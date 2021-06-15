package com.neivor.vertx.Querys;

import com.neivor.vertx.Bd.Consulta;
import com.neivor.vertx.Models.Condominio;

import java.sql.SQLException;
/**

 * Esta clase define las diferentes consultas sql que se realiza a la tabla condomino

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */



public class CondominioQuery {
    /**
     * Obtiene un condomino por su id(numero de cliente).
     * @param idCondominio id con el cual se desea realizar la filtracion
     * @return condomino retorna un objeto de tipo condomino.
     */
    public Condominio obtenerPorid(String idCondominio) throws SQLException {
        String sql = "SELECT * FROM condomino WHERE condomino.idcondomino = ? LIMIT 1";

        Consulta consulta = new Consulta();
        consulta.agregarParametro(idCondominio);

        consulta.establecerDatosRegistro(sql);

        Condominio condominio = new Condominio();
        condominio.setIdCondomino(consulta.obtenerValorCampo("idcondomino"));
        condominio.setNombrePagador(consulta.obtenerValorCampo("nombrePagador"));
        condominio.setDocumentoPagador(consulta.obtenerValorCampo("documentoNum"));
        condominio.setNumeroCasaDep(consulta.obtenerValorCampo("numeroCasaDep"));
        condominio.setValoraPagar(consulta.obtenerValorCampo("valoraPagar"));
        condominio.setServicios_idservicios(consulta.obtenerValorCampo("servicios_idservicios"));
        return condominio;

    }
    /**
     * Consulta si existe un condomino
     * @param nombrePagador,documento,numCasa,idServ datos que se necesitan para la filtracion
     * @return retorna id si existe condomino o 0(cero) en caso de no existir.
     */
    public int obtenerCondomino(String nombrePagador, String documento, String numCasa, String idServ) throws SQLException {
        String sql = "SELECT `condomino`.`IDCONDOMINO`, `condomino`.`NOMBREPAGADOR`, `condomino`.`DOCUMENTONUM`, `condomino`.`NUMEROCASADEP`," +
                " `deuda_condomino`.`IDSERVICIOS` FROM `condomino` " +
                "INNER JOIN `deuda_condomino` ON `condomino`.`IDCONDOMINO` = `deuda_condomino`.`IDCONDOMINO` " +
                "WHERE `condomino`.`NOMBREPAGADOR` = ? AND " +
                "`condomino`.`DOCUMENTONUM` = ? AND " +
                "`condomino`.`NUMEROCASADEP` = ? AND " +
                "`deuda_condomino`.`IDSERVICIOS` = ?";
        Consulta consulta = new Consulta();
        consulta.agregarParametro(nombrePagador);
        consulta.agregarParametro(documento);
        consulta.agregarParametro(numCasa);
        consulta.agregarParametro(idServ);

        String resultado = consulta.obtenerCampo(sql);
        resultado = (!resultado.equals("")) ? resultado : "0";
        int id = Integer.parseInt(resultado);
        return Math.max(id, 0);
    }


}
