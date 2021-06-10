package com.neivor.vertx;

import com.neivor.vertx.Constantes.CodErrores;
import com.neivor.vertx.Constantes.EstadoOrdenPago;
import com.neivor.vertx.Dao.CondominioDao;
import com.neivor.vertx.Dao.Ordenes_pagoDao;
import com.neivor.vertx.Models.Condominio;
import com.neivor.vertx.Models.Ordenes_pago;
import com.neivor.vertx.Querys.CondominioQuery;
import com.neivor.vertx.Querys.OrdenPagoQuery;
import com.neivor.vertx.Util.UtilJson;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import sun.security.provider.certpath.Vertex;

import java.sql.SQLException;

public class App 
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);
//        router.route().handler(BodyHandler.create());
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
                    response.putHeader("content-type","application/json");
                    CondominioQuery condominio = new CondominioQuery();
                    String condominioString = null;
                    try {
                        System.out.println("condominio es: "+ condominio.obtenerPorid(id));
                        condominioString = UtilJson.objetoAJsonString(condominio.obtenerPorid(id));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    System.out.println(condominioString);
                    response.end(condominioString);
                });
        Route consultaExisteCondominio = router
                .post("/condomino")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type","application/json");
                    JsonObject body = routingContext.getBodyAsJson();
                    String nombrePagador =body.getString("nombrePagador");
                    String documentoNum =body.getString("documentoNum");
                    String numeroCasaDep =body.getString("numeroCasaDep");
                    String idservicios= body.getString("servicios_idservicios");
                    String montoPago = body.getString("valoraPagar");
                    CondominioQuery condomino = new CondominioQuery();
                    try {
                        int id = condomino.obtenerCondomino(nombrePagador,documentoNum,numeroCasaDep,idservicios);
                        if (id > 0){
                            Ordenes_pago ordenesPago = new Ordenes_pago();
                            ordenesPago.setIdcondominio(""+id);
                            ordenesPago.setEstadoOrdenPago(EstadoOrdenPago.INGRESADO);
                            ordenesPago.setServicios_idservicios(idservicios);
                            ordenesPago.setMontoPago(montoPago);
                            Ordenes_pagoDao ordenesPagoDao = new Ordenes_pagoDao();
                            ordenesPagoDao.guardar(ordenesPago);
                            OrdenPagoQuery ordenPagoQuery = new OrdenPagoQuery();
                            String consultaOrden = ordenPagoQuery.obtenerNumeroOrden();
                            String resp = consultaOrden;
                            response.end(resp);
                        }else{
                            response.end(CodErrores.BUSQUEDA_SIN_RESULTADO);
                        }
                    } catch (SQLException throwables) {
                        System.out.println(throwables);
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
                httpServer
                        .requestHandler(router::accept)
                        .listen(8091);
    }

}
