package com.neivor.vertx.Querys;
import com.neivor.vertx.Bd.Consulta;
import com.neivor.vertx.Models.Condominio;

import java.sql.SQLException;

public class CondominioQuery {

    public Condominio obtenerPorid(String idCondominio) throws SQLException {
        String sql = "SELECT * FROM condominio WHERE condominio.idcondominio = ? LIMIT 1";

        Consulta consulta = new Consulta();
        consulta.agregarParametro(idCondominio);

        consulta.establecerDatosRegistro(sql);

        Condominio condominio = new Condominio();
        condominio.setIdCondominio(consulta.obtenerValorCampo("idcondominio"));
        condominio.setNombrePagador(consulta.obtenerValorCampo("nombrePagador"));
        condominio.setDocumentoPagador(consulta.obtenerValorCampo("documentoPagador"));
        condominio.setNumeroCasaDep(consulta.obtenerValorCampo("numeroCasaDep"));
        condominio.setValoraPagar(consulta.obtenerValorCampo("valoraPagar"));
        return condominio;

    }


}
