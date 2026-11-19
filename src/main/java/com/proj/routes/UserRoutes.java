package com.proj.routes;

import io.javalin.config.RoutesConfig;

import java.util.Map;

import com.proj.exceptions.UserNotFoundException;
import com.proj.model.UserModel;
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
            UserModel user = ctx.bodyAsClass(UserModel.class);
            this.controller.insertReg(user);
            ctx.status(201);
        });

        configRoute.put("/users/{id}", ctx -> {
            UserModel user = ctx.bodyAsClass(UserModel.class);
            this.controller.updateReg(
                Integer.parseInt(ctx.pathParam("id")),
                user
            );
            ctx.status(200);
        });

        configRoute.delete("/users/{id}", ctx -> {
            this.controller.deleteUser(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(204);
        });

        configRoute.exception(
            UserNotFoundException.class,
            (e, ctx) -> {
                ctx.status(404)
                .json(Map.of("error", e.getMessage()));
            }
        );
    }
}
