package com.neivor.vertx.Querys;
import com.neivor.vertx.Bd.Consulta;
import java.sql.SQLException;


public class OrdenPagoQuery {

    public String obtenerNumeroOrden() throws SQLException {
        String sql = "SELECT MAX(IDORDENES_PAGO) AS id FROM ordenes_pago";
        Consulta consulta = new Consulta();
        return consulta.obtenerCampo(sql);
    }

}
