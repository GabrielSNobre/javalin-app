package com.proj.repository;

import com.proj.database.DatabaseConfig;

import com.proj.model.UserModel;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final DatabaseConfig dbConfig;
    private Connection conn;

    public UserRepository(DatabaseConfig dbConfig) throws SQLException{
        this.dbConfig = dbConfig;
        try{
            this.conn = dbConfig.getConn();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<UserModel> fetchAll() throws SQLException {
        ArrayList<UserModel> users = new ArrayList<UserModel>();
        try(
            Connection conn = this.dbConfig.getConn();
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from tb_user;";
            ResultSet results = stmt.executeQuery(sql);
            while(results.next()){
                UserModel user = new UserModel();
                user.setNome(results.getString("nome"));
                user.setEmail(results.getString("email"));
                users.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public HashMap<String,String> fetchUserById(int id) throws SQLException{
        PreparedStatement stmtQuery = null;
        String sql = "Select id, nome, email from tb_user where id = ?";
        ResultSet rsUserData = null;
        HashMap<String,String> data = new HashMap<>();
        try{
            stmtQuery = this.conn.prepareStatement(sql);
            stmtQuery.setInt(1, id);
            rsUserData = stmtQuery.executeQuery();
            while(rsUserData.next()){
                data.put("nome",rsUserData.getString("nome"));
                data.put("email",rsUserData.getString("email"));
            }
        } catch(SQLException e){
            System.out.printf("Erro ao processar query: '%s'\nStackTrace: %s",sql,e.toString());
        }
            return data;
    }
}
