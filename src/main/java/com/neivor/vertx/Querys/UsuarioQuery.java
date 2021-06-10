package com.neivor.vertx.Querys;
import com.neivor.vertx.Bd.Consulta;
import com.neivor.vertx.Models.Condominio;

import java.sql.SQLException;

public class UsuarioQuery {
    public int usurioLogeado(String usuario, String pass, String idEntidad) throws SQLException {
        String sql = "SELECT count(*) FROM `usuarios` WHERE" +
                " `usuarios`.`idEntidad` = ? AND" +
                " `usuarios`.`nombreUsuario` = ? AND" +
                " `usuarios`.`usuarioPass` = ?";
        Consulta consulta = new Consulta();
        consulta.agregarParametro(idEntidad);
        consulta.agregarParametro(usuario);
        consulta.agregarParametro(pass);
        return Integer.parseInt(consulta.obtenerCampo(sql));
    }
}
