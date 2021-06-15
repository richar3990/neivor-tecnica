package com.neivor.vertx.Dao;

import com.neivor.vertx.Bd.Abm;
import com.neivor.vertx.Models.Pago;

import java.sql.SQLException;
/**

 * Esta clase define las Altas , Bajas y modificaciones de la tabla Pagos

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */

/**
 * Guarda un nuevo pago.
 */

public class PagoDao {
    public void guardar(Pago pago) throws SQLException{
        Abm abmGuardador = new Abm("pago");
        abmGuardador.agregarColumna("IDUSUARIOS",pago.getIDUSUARIOS());
        abmGuardador.agregarColumna("IDORDENES_PAGO",pago.getIDORDENES_PAGO());
        abmGuardador.agregarColumna("MONTOTOTAL",pago.getMONTOTOTAL());
        abmGuardador.agregarColumna("FECHAPAGO",pago.getFECHAPAGO());
        abmGuardador.agregarColumna("NOMBREFACTURA",pago.getNOMBREFACTURA());
        abmGuardador.agregarColumna("NITFACTURA",pago.getNITFACTURA());
        abmGuardador.ejecutarInsert();
    }
}
