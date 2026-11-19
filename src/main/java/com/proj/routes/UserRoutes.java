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

    public void getUsersAll(RoutesConfig configRoute){
        configRoute.get("/users",ctx ->{
            ctx.json(this.controller.getAllUsers())
            .status(200);
        });
    }
}
