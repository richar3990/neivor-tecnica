package com.neivor.vertx;

import com.neivor.vertx.Constantes.EstadoOrdenPago;
import com.neivor.vertx.Dao.CondominioDao;
import com.neivor.vertx.Dao.DeudaDao;
import com.neivor.vertx.Dao.Ordenes_pagoDao;
import com.neivor.vertx.Dao.PagoDao;
import com.neivor.vertx.Models.*;
import com.neivor.vertx.Querys.CondominioQuery;
import com.neivor.vertx.Querys.DeudaCondominoQuery;
import com.neivor.vertx.Querys.OrdenPagoQuery;
import com.neivor.vertx.Querys.UsuarioQuery;
import com.neivor.vertx.Util.UtilFecha;
import com.neivor.vertx.Util.UtilJson;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import sun.security.provider.certpath.Vertex;

import java.sql.SQLException;
import java.util.ArrayList;
/**

 * Esta clase define la raiz de la api en la cual se definen las rutas y respuestas que obtendra el
 * front-end que lo consumira.

 * @author: Richar Daniel Meza Silva

 * @version: 14/06/2021


 */

public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedHeader("Access-Control-Request-Method")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Headers")
                .allowedHeader("X-PINGARUNER")
                .allowedHeader("Content-Type"))
                .handler(BodyHandler.create());

        Route consultaCondominio = router.get("/condominio/:id")
                .handler(routingContext -> {
                    String id = routingContext.request().getParam("id");
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "application/json");
                    CondominioQuery condominio = new CondominioQuery();
                    String condominioString = null;
                    try {
                        condominioString = UtilJson.objetoAJsonString(condominio.obtenerPorid(id));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    response.end(condominioString);
                });
        Route consultarUsuario = router
                .post("/login")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "application/json");
                    JsonObject body = routingContext.getBodyAsJson();
                    String nombreuser = body.getString("nombreUsuario");
                    String idEntidad = body.getString("idEntidad");
                    String pass = body.getString("usuarioPass");
                    UsuarioQuery usuario = new UsuarioQuery();
                    try {
                        int id = usuario.usurioLogeado(nombreuser, pass, idEntidad);
                        if (id > 0) {
                            response.end();
                        } else {
                            response.setStatusMessage("error");
                            response.end();
                        }

                    } catch (SQLException throwables) {
                    }
                });
        Route consultaExisteCondominio = router
                .post("/condomino")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "application/json");
                    JsonObject body = routingContext.getBodyAsJson();
                    String nombrePagador = body.getString("nombrePagador");
                    String documentoNum = body.getString("documentoNum");
                    String numeroCasaDep = body.getString("numeroCasaDep");
                    String idservicios = body.getString("servicios_idservicios");
                    String montoPago = body.getString("valoraPagar");
                    CondominioQuery condomino = new CondominioQuery();
                    try {
                        int id = condomino.obtenerCondomino(nombrePagador, documentoNum, numeroCasaDep, idservicios);
                        if (id > 0) {
                            Ordenes_pago ordenesPago = new Ordenes_pago();
                            ordenesPago.setIdcondominio("" + id);
                            ordenesPago.setEstadoOrdenPago(EstadoOrdenPago.INGRESADO);
                            ordenesPago.setServicios_idservicios(idservicios);
                            ordenesPago.setMontoPago(montoPago);
                            Ordenes_pagoDao ordenesPagoDao = new Ordenes_pagoDao();
                            ordenesPagoDao.guardar(ordenesPago);
                            OrdenPagoQuery ordenPagoQuery = new OrdenPagoQuery();
                            String consultaOrden = ordenPagoQuery.obtenerNumeroOrden();
                            response.end(consultaOrden);
                        } else {
                            response.setStatusMessage("error");
                            response.end();
                        }
                    } catch (SQLException throwables) {
                    }
                });
        Route insertarCondominio = router
                .post("/condominio")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    JsonObject body = routingContext.getBodyAsJson();
                    Condominio condominio = new Condominio();
                    condominio.setNombrePagador(body.getString("nombrePagador"));
                    condominio.setDocumentoPagador(body.getString("documentoPagador"));
                    condominio.setNumeroCasaDep(body.getString("numeroCasaDep"));
                    condominio.setValoraPagar(body.getString("valoraPagar"));
                    CondominioDao condominioDao = new CondominioDao();
                    try {
                        condominioDao.guardar(condominio);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    response.end();
                });
        //CONSULTA POR NUMERO DE ORDEN DE PAGO
        Route consultaOrdenPago = router
                .get("/orden-pago/:id")
                .handler(routingContext -> {
                    String id = routingContext.request().getParam("id");
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "application/json");
                    OrdenPagoQuery ordenPagoQuery = new OrdenPagoQuery();

                    try {
                        Ordenes_pago existeOrden;
                        existeOrden = ordenPagoQuery.existeOrden(id);
                        int existe = (existeOrden.getIdordenes_pago() == null) ? 0 : 1;

                        if (existe > 0) {
                            String montoOrden = existeOrden.getMontoPago();
                            String idServicio = existeOrden.getServicios_idservicios();
                            String idCondomino = existeOrden.getIdcondominio();
                            int credito = idServicio.equals("2") ? 0 : 1;
//
                            DeudaCondomino deudaCondomino;
                            DeudaCondominoQuery deudaCondominoQuery = new DeudaCondominoQuery();
                            deudaCondomino = deudaCondominoQuery.obtnerDatosDeuda(idCondomino, idServicio);
                            String resp = UtilJson.objetoAJsonString(deudaCondomino);
                            response.setStatusMessage(montoOrden);
                            response.end(resp);
                        } else {
                            CodErrores errores = new CodErrores();
                            errores.setCodError("301");
                            errores.setDescripcion("CÓDIGO DE DEPOSITANTE NO EXISTE");
                            String resp = UtilJson.objetoAJsonString(errores);
                            response.setStatusMessage("error");
                            response.end(resp);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                });
        //DETALLE DE CUOTAS
        Route detalleCuotas = router
                .get("/consulta-cuotas/:id")
                .handler(routingContext -> {
                    String id = routingContext.request().getParam("id");
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "application/json");
                    DeudaCondominoQuery deudaCondominoQuery = new DeudaCondominoQuery();
                    try {
                        DeudaCondomino existeDeuda;
                        existeDeuda = deudaCondominoQuery.obtnerDatosDeuda(id, "2");
                        int existe = (existeDeuda.getIMPORTEADEUDADO() == null) ? 0 : 1;
                        if (existe > 0) {
                            ArrayList datosCuotas = deudaCondominoQuery.obtenerDatosCuotas(existeDeuda.getIDDEUDA_CONDOMINO());
                            String resp = UtilJson.objetoAJsonString(datosCuotas);
                            response.end(resp);
                        } else {
                            CodErrores errores = new CodErrores();
                            errores.setCodError("302");
                            errores.setDescripcion("CLIENTE NO POSEE CUOTAS");
                            String resp = UtilJson.objetoAJsonString(errores);
                            response.setStatusMessage("errores");
                            response.end(resp);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
        //CONSULTA POR NUMERO DE CONDOMINO
        Route consultaNumeroCliente = router
                .get("/consulta-condomino/:id/:idServicio")
                .handler(routingContext -> {
                    String id = routingContext.request().getParam("id");
                    String idServicio = routingContext.request().getParam("idServicio");
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "application/json");
                    DeudaCondominoQuery deudaCondominoQuery = new DeudaCondominoQuery();
                    try {
                        DeudaCondomino existeDeuda;
                        existeDeuda = deudaCondominoQuery.obtnerDatosDeuda(id, idServicio);
                        int existe = (existeDeuda.getIMPORTEADEUDADO() == null) ? 0 : 1;
                        if (existe > 0) {
                            String resp = UtilJson.objetoAJsonString(existeDeuda);
                            response.end(resp);

                        } else {
                            CodErrores errores = new CodErrores();
                            errores.setCodError("301");
                            errores.setDescripcion("CÓDIGO DE DEPOSITANTE NO EXISTE");
                            String resp = UtilJson.objetoAJsonString(errores);
                            response.end(resp);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                });
        //CONSULTA POR NUMERO DE CONDOMINO
        Route pagar = router
                .post("/pago-servicio")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "application/json");
                    JsonObject body = routingContext.getBodyAsJson();
                    UtilFecha fecha = new UtilFecha();
                    String idUsuario = "1";
                    String idOrdenPago = body.getString("IDORDENES_PAGO");
                    String montoTotal = body.getString("MONTOTOTAL");
                    String fechaActual = fecha.fechaBd();
                    String nitNumero = body.getString("NITFACTURA");
                    String nombreFactura = body.getString("NOMBREFACTURA");
                    String idDeuda = body.getString("IDDEUDA");

                    Pago pago = new Pago();
                    pago.setIDUSUARIOS(idUsuario);
                    pago.setIDORDENES_PAGO(idOrdenPago);
                    pago.setMONTOTOTAL(montoTotal);
                    pago.setFECHAPAGO(fechaActual);
                    pago.setNITFACTURA(nitNumero);
                    pago.setNOMBREFACTURA(nombreFactura);

                    try {
                        DeudaCondominoQuery deudaCondominoQuery = new DeudaCondominoQuery();
                        PagoDao pagoDao = new PagoDao();

                        DeudaDao actualizarDeuda = new DeudaDao();
                        Ordenes_pagoDao actualizarEstado = new Ordenes_pagoDao();
                        String monto = deudaCondominoQuery.obtenerMontoTotal(idDeuda).getIMPORTEADEUDADO();
                        float montoTotalQuery = Float.parseFloat(deudaCondominoQuery.obtenerMontoTotal(idDeuda).getIMPORTEADEUDADO());
                        float motoTotalFront = Float.parseFloat(montoTotal);
                        int montoFrontInt = (int)motoTotalFront;
                        int montoInt = (int)montoTotalQuery;
                        if (montoFrontInt>montoInt){
                            response.setStatusMessage("error monto supera la deuda");
                            response.end();
                        }else{
                            int montoGuardar = montoInt - montoFrontInt;
                            pagoDao.guardar(pago);
                            actualizarDeuda.actulizarDeuda(""+montoGuardar, idDeuda);
                            actualizarEstado.actulizarEstado(idOrdenPago);
                            response.end();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
        httpServer
                .requestHandler(router::accept)
                .listen(8091);
        System.out.println("Servidor corriendo en puerto:8091");
    }

}
