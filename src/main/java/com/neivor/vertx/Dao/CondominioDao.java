package com.neivor.vertx.Dao;
import com.neivor.vertx.Bd.Abm;
import com.neivor.vertx.Models.Condominio;
import java.sql.SQLException;
/**

 * Esta clase define las Altas , Bajas y modificaciones de la tabla Condomino

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */


/**
 * Guarda un nuevo Condomino.
 * @deprecated Viendo mas detenido el caso no hizo falta poder guardar un nuevo condomino
 */
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
