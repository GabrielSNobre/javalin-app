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

    public void insertReg(UserModel userModel) throws SQLException{
        try{
            this.repository.insertUser(userModel);
            System.out.println("Registro Gerado");
        } catch(SQLException e){
        System.out.println("Erro ao criar o registro");
            e.printStackTrace();
        }
    }

    public void updateReg(int id, UserModel user) throws SQLException {
        try{
            this.repository.updateUser(id,user);
        } catch(SQLException e){
            System.out.println("Erro ao atualizar o registro");
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) throws SQLException{
        try{
            this.repository.deleteUser(id);

        } catch(SQLException e){
            System.out.println("Erro ao apagar o registro");
            e.printStackTrace();
        }
    }
}
