package com.assignment.interactions.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        GoogleStorageUtils gse = new GoogleStorageUtils();

        Router router = Router.router(vertx);

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                .allowedHeader("Access-Control-Allow-Method")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Content-Type"));

        router.get("/").handler(rc -> {
            rc.response().end("Welcome!");
        });
        router.get("/api/getclicks").handler(rc -> {
            rc.response().end(
                    new JsonObject().put("name", "clicks").put("amount", gse.read()).encode()
            );
        });
        router.get("/api/increment").handler(rc -> {
            rc.response().end(
                    new JsonObject().put("name", "clicks").put("amount", gse.write()).encode()
            );
        });

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);

    }
}
