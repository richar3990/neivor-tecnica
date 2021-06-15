package com.neivor.vertx.Util;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**

 * Esta clase define las diferentes metodos para la manipulacion de fechas.

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */

public class UtilFecha {
    public static String fechaBd() {
        Calendar fecha = new GregorianCalendar();
        String anio = Integer.toString(fecha.get(Calendar.YEAR));
        String mes = "0"+ Integer.toString(fecha.get(Calendar.MONTH));
        String dia = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
        return anio + mes + dia;
    }
}
