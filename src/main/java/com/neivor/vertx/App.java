package com.neivor.vertx;

import com.neivor.vertx.Dao.CondominioDao;
import com.neivor.vertx.Models.Condominio;
import com.neivor.vertx.Querys.CondominioQuery;
import com.neivor.vertx.Util.UtilJson;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import sun.security.provider.certpath.Vertex;

import java.sql.SQLException;

public class App 
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

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
