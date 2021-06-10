package com.neivor.vertx.Querys;
import com.neivor.vertx.Bd.Consulta;
import com.neivor.vertx.Models.Condominio;

import java.sql.SQLException;

public class CondominioQuery {

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
    public int obtenerCondomino(String nombrePagador, String documento, String numCasa, String idServ)throws SQLException{
        String sql = "SELECT `condomino`.`idcondomino` AS `id` " +
                "FROM `condomino` WHERE" +
                " `condomino`.`nombrePagador` = ? and" +
                " `condomino`.`documentoNum` = ? and" +
                " `condomino`.`numeroCasaDep` = ? and" +
                " `condomino`.`servicios_idservicios` = ? ";
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
