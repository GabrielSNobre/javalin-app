package com.proj.controller;

import com.proj.repository.UserRepository;
import com.proj.database.DatabaseConfig;
import io.javalin.config.JavalinConfig;

import java.sql.*;

import java.util.List;
import java.util.Map;
import java.util.Locale.Category;
import java.util.HashMap;

public class UserController {
    private UserRepository repository;
    private DatabaseConfig dbConf;

    public UserController(){

    }
    public UserController(UserRepository repository, DatabaseConfig dbConfig){
        repository = this.repository;
        dbConfig = this.dbConf;
    }

    public HashMap<String,String> getUserById(int id) throws SQLException{
        HashMap<String,String> userData = new HashMap<String,String>();

        UserRepository userRepo = new UserRepository(this.dbConf.getConn());

        try(var rsUserData = userRepo.fetchUserById(id);){
            while(rsUserData.next()){
                userData.put("nome", rsUserData.getString("nome"));
                userData.put("email", rsUserData.getString("email"));
            }
            System.out.println("Resultado: " + userData.toString());
        } catch (SQLException e){
            System.out.println("Erro durante a consulta: " + e.toString());
        }
        return userData;
    }

}
