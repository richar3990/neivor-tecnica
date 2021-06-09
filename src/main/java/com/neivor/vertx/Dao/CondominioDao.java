package com.neivor.vertx.Dao;
import com.neivor.vertx.Bd.Abm;
import com.neivor.vertx.Models.Condominio;
import java.sql.SQLException;

public class CondominioDao {
    public void guardar(Condominio condominio) throws SQLException{
        Abm abmGuardador = new Abm("condominio");
        abmGuardador.agregarColumna("nombrePagador", condominio.getNombrePagador());
        abmGuardador.agregarColumna("documentoPagador", condominio.getDocumentoPagador());
        abmGuardador.agregarColumna("numeroCasaDep", condominio.getNumeroCasaDep());
        abmGuardador.agregarColumna("valoraPagar", condominio.getValoraPagar());
        abmGuardador.ejecutarInsert();
    }
}
