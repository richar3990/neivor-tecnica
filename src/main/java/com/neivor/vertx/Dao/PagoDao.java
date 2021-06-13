package com.neivor.vertx.Dao;

import com.neivor.vertx.Bd.Abm;
import com.neivor.vertx.Models.Pago;

import java.sql.SQLException;

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
