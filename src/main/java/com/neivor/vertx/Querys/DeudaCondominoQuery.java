package com.neivor.vertx.Querys;
import com.neivor.vertx.Bd.Consulta;
import com.neivor.vertx.Models.DeudaCondomino;
import com.neivor.vertx.Models.DetallesCuota;
import java.sql.SQLException;
import java.util.ArrayList;

/**

 * Esta clase define las diferentes consultas sql que se realiza a la tabla deuda_condomino

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */


public class DeudaCondominoQuery {
    /**
     * Obtiene un condomino por su id(numero de cliente).
     * @param condomino,idServicio con el id del condomino y id del sercicio para obtener la deuda
     * @return condomino retorna un objeto de tipo condomino con todos los datos.
     */
    public DeudaCondomino obtnerDatosDeuda(String condomino, String idServicio) throws SQLException {
        String sql = "SELECT `deuda_condomino`.`IDDEUDA_CONDOMINO`, `deuda_condomino`.`IDSERVICIOS`, " +
                "`deuda_condomino`.`IDCONDOMINO`, `deuda_condomino`.`IMPORTEADEUDADO`, " +
                "`deuda_condomino`.`IMPORTEMINIMO`, `deuda_condomino`.`IMPORTECOMISION`, " +
                "`servicios`.`CODIGOSERVICIO`, `servicios`.`NOMBRESERVICIO`, `condomino`.`NOMBREPAGADOR` " +
                "FROM `deuda_condomino` INNER JOIN `servicios` ON " +
                "`servicios`.`IDSERVICIOS` = `deuda_condomino`.`IDSERVICIOS` " +
                "INNER JOIN `condomino` ON `deuda_condomino`.`IDCONDOMINO` = `condomino`.`IDCONDOMINO` " +
                "WHERE `deuda_condomino`.`IDSERVICIOS` = ? AND `deuda_condomino`.`IDCONDOMINO` = ?";
        Consulta consulta = new Consulta();
        consulta.agregarParametro(idServicio);
        consulta.agregarParametro(condomino);

        consulta.establecerDatosRegistro(sql);
        DeudaCondomino deudaCondomino = new DeudaCondomino();
        deudaCondomino.setIDDEUDA_CONDOMINO(consulta.obtenerValorCampo("IDDEUDA_CONDOMINO"));
        deudaCondomino.setIDSERVICIOS(consulta.obtenerValorCampo("CODIGOSERVICIO")
        +" - "+consulta.obtenerValorCampo("NOMBRESERVICIO"));
        deudaCondomino.setIMPORTEADEUDADO(consulta.obtenerValorCampo("IMPORTEADEUDADO"));
        deudaCondomino.setIMPORTEMINIMO(consulta.obtenerValorCampo("IMPORTEMINIMO"));
        deudaCondomino.setIDCONDOMINO(consulta.obtenerValorCampo("NOMBREPAGADOR"));
        return deudaCondomino;


    }
    /**
     * Obtiene detalle de la cuota de un condomino
     * @param idDeuda con el id de la deuda se filtra los detalles de cuotas
     * @return lista de las cuotas.
     */
    public ArrayList<DetallesCuota> obtenerDatosCuotas(String idDeuda) throws SQLException{
        ArrayList<DetallesCuota> detalles = new ArrayList<>();
        String sql ="SELECT `detallescuota`.`IDDETALLECUOTA`, `detallescuota`.`IDDEUDA_CONDOMINO`," +
                " `detallescuota`.`NUMEROCUOTA`, `detallescuota`.`DETALLECUOTA`, " +
                "`detallescuota`.`FECHAVENCIMIENTO`, `detallescuota`.`IMPORTECUOTA`, " +
                "`detallescuota`.`IMPORTECOMISION` FROM `detallescuota` " +
                "WHERE `detallescuota`.`IDDEUDA_CONDOMINO` = ?";
        Consulta consulta = new Consulta();
        consulta.agregarParametro(idDeuda);
        DetallesCuota detallesCuota;
        String[][] datosCuotas = consulta.obtenerRegistros(sql);
        for (String[] datosCuota : datosCuotas){
            detallesCuota = new DetallesCuota();
            detallesCuota.setIDDETALLECUOTA(datosCuota[0]);
            detallesCuota.setIDDEUDA_CONDOMINO(datosCuota[1]);
            detallesCuota.setNUMEROCUOTA(datosCuota[2]);
            detallesCuota.setDETALLECUOTA(datosCuota[3]);
            detallesCuota.setFECHAVENCIMIENTO(datosCuota[4]);
            detallesCuota.setIMPORTECUOTA(datosCuota[5]);
            detallesCuota.setIMPORTECOMISION(datosCuota[6]);
            detalles.add(detallesCuota);
        }
        return detalles;
    }
    /**
     * Obtiene Monto total de una deuda
     * @param idDeuda con el id de la deuda se filtra el detalle de la deuda
     * @return objeto de tipo deudaCondomino con los datos de la deuda.
     */
    public DeudaCondomino obtenerMontoTotal(String idDeuda) throws SQLException {
        String sql = "SELECT `deuda_condomino`.`IDDEUDA_CONDOMINO`, `deuda_condomino`.`IDSERVICIOS`," +
                " `deuda_condomino`.`IDCONDOMINO`, `deuda_condomino`.`IMPORTEADEUDADO`, " +
                "`deuda_condomino`.`IMPORTEMINIMO`, `deuda_condomino`.`IMPORTECOMISION` " +
                "FROM `deuda_condomino` " +
                "WHERE `deuda_condomino`.`IDDEUDA_CONDOMINO` = ?";
        Consulta consulta = new Consulta();
        consulta.agregarParametro(idDeuda);
        consulta.establecerDatosRegistro(sql);
        DeudaCondomino deudaCondomino = new DeudaCondomino();
        deudaCondomino.setIDSERVICIOS(consulta.obtenerValorCampo("IDSERVICIOS"));
        deudaCondomino.setIDCONDOMINO(consulta.obtenerValorCampo("IDCONDOMINO"));
        deudaCondomino.setIMPORTECOMISION(consulta.obtenerValorCampo("IMPORTECOMISION"));
        deudaCondomino.setIMPORTEADEUDADO(consulta.obtenerValorCampo("IMPORTEADEUDADO"));
        deudaCondomino.setIMPORTEMINIMO(consulta.obtenerValorCampo("IMPORTEMINIMO"));

        return deudaCondomino;




    }
}
