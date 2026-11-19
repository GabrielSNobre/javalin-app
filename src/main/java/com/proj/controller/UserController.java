package com.proj.controller;

import com.proj.model.UserModel;
import com.proj.repository.UserRepository;
import com.proj.database.DatabaseConfig;

import io.javalin.config.JavalinConfig;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Locale.Category;
import java.util.HashMap;

public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository){
        this.repository = repository ;
    }

    public UserModel getUserById(int id) throws SQLException{
        UserModel user = new UserModel();
        user = this.repository.fetchUserById(id);
        System.out.println("Resultado: " + user.toString());
        return user;
    }

    public ArrayList<UserModel> getAllUsers() throws SQLException{
        ArrayList<UserModel> userData = this.repository.fetchAll();
        System.out.println("Resultado: " + userData.toString());
        return userData;
    }

}
