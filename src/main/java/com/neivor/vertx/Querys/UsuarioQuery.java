package com.neivor.vertx.Querys;
import com.neivor.vertx.Bd.Consulta;
import com.neivor.vertx.Models.Condominio;

import java.sql.SQLException;
/**

 * Esta clase define las diferentes consultas sql que se realiza a la tabla usuarios

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */

public class UsuarioQuery {
    /**
     * Verifica si un usuario esta logeado
     * @param usuario,pass,idEntidad usuario, contrasena y la entidad.
     * @return cantidad de usuarios con los datos ingresados.
     */
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
