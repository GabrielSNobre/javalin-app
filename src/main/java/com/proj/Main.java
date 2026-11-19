package com.proj;

import java.sql.Connection;
import java.sql.SQLException;

import com.proj.database.DatabaseConfig;
import com.proj.repository.UserRepository;
import com.proj.controller.UserController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main{
    public static void main(String[] args) throws SQLException {
        DatabaseConfig dbConf = new DatabaseConfig();
        UserRepository userRepository = new UserRepository(dbConf);
        UserController userControler = new UserController(userRepository);
        try{
            Connection conn = dbConf.getConn();
            dbConf.initDB();
            userControler.getUserById(2);
            if(conn != null){
                System.out.println("Conexão Estabelecida");
            } else {
                System.out.println("Erro de Conexão");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        var app = Javalin.create(
            config -> {
                config.staticFiles.add("src/main/public",Location.EXTERNAL);
                config.routes.get("/", ctx -> {
                    ctx.result("Hello");
                });
                // config.routes.get("/user/{id}", ctx -> {
                //     ctx.json(
                //         userControler.getUserById(
                //             Integer.parseInt(ctx.pathParam("id"))
                //         )
                //     );
                //     ctx.status(200);
                // });
        });
        app.start(7070);
    }
}
