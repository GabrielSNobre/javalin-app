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

    public ArrayList<HashMap<String,String>> getUserById(int id) throws SQLException{
        ArrayList<HashMap<String,String>> userData = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> data = this.repository.fetchUserById(id);
        userData.add(data);
        System.out.println("Resultado: " + userData.toString());
        return userData;
    }

    public ArrayList<UserModel> getAllUsers() throws SQLException{
        ArrayList<UserModel> userData = this.repository.fetchAll();
        System.out.println("Resultado: " + userData.toString());
        return userData;
    }

}
