package com.proj.routes;

import io.javalin.config.RoutesConfig;

import com.proj.controller.UserController;

public class UserRoutes {

    private UserController controller;
    public UserRoutes(){

    }
    public UserRoutes(UserController controller){
        this.controller = controller;
    }

    public void register(RoutesConfig configRoute){
        configRoute.get("/users",ctx ->{
            ctx.json(this.controller.getAllUsers().toString())
            .status(200);
        });
        configRoute.get("/users/{id}", ctx -> {
            ctx.json(this.controller.getUserById(Integer.parseInt(ctx.pathParam("id"))))
            .status(200);
        });

        configRoute.post("/users", ctx -> {
            this.controller.insertUser();
        });
    }
}
