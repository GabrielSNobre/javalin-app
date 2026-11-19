package com.proj.controller;

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
    private UserRepository repository;

    public UserController(){

    }
    public UserController(UserRepository repository){
        this.repository = repository ;
    }

    public ArrayList<HashMap<String,String>> getUserById(int id) throws SQLException{
        ArrayList<HashMap<String,String>> userData = new ArrayList<HashMap<String,String>>();

        userData = this.repository.fetchAll();
        // try(var rsUserData = this.repository.fetchAll();){
        //     while(rsUserData.next()){
        //         userData.put("nome", rsUserData.getString("nome"));
        //         userData.put("email", rsUserData.getString("email"));
        //     }
        // } catch (SQLException e){
        //     System.out.println("Erro durante a consulta: " + e.toString());
        // }
        System.out.println("Resultado: " + userData.toString());
        return userData;
    }

}
