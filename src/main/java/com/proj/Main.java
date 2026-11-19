package com.proj;

import java.sql.Connection;
import java.sql.SQLException;

import com.proj.database.DatabaseConfig;
import com.proj.repository.UserRepository;
import com.proj.controller.UserController;
import com.proj.routes.UserRoutes;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main{
    public static void main(String[] args) throws SQLException {

        DatabaseConfig dbConf = new DatabaseConfig();
        UserRepository userRepository = new UserRepository(dbConf);
        UserController userController = new UserController(userRepository);
        UserRoutes userRoutes = new UserRoutes(userController);

        var app = Javalin.create(
            config -> {
                // Definição de Arquivos do Front
                config.staticFiles.add("src/main/public",Location.EXTERNAL);

                // Construido Roteamento Externo
                userRoutes.register(config.routes);
        });
        app.start(7070);
    }
}
