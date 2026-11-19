package com.proj.repository;

import com.proj.database.DatabaseConfig;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private DatabaseConfig dbConfig;
    private Connection conn;

    public UserRepository(){}

    public UserRepository(DatabaseConfig dbConfig) throws SQLException{
        this.dbConfig = dbConfig;
        try{
            this.conn = dbConfig.getConn();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String,String>> fetchAll() throws SQLException {
        ArrayList<HashMap<String,String>> users = new ArrayList<HashMap<String,String>>();
        try(
            Connection conn = this.dbConfig.getConn();
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from tb_user;";
            ResultSet results = stmt.executeQuery(sql);
            while(results.next()){
                HashMap<String,String> userData = new HashMap<>();
                userData.put("nome",results.getString("nome"));
                userData.put("email",results.getString("email"));
                users.add(userData);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public ResultSet fetchUserById(int id) throws SQLException{
        PreparedStatement stmtQuery = null;
        String sql = "Select id, nome, email from tb_user where id = ?";
        ResultSet rsUserData = null;
        try{
            stmtQuery = this.conn.prepareStatement(sql);
            stmtQuery.setInt(1, id);
            rsUserData = stmtQuery.executeQuery();
        } catch(SQLException e){
            System.out.printf("Erro ao processar query: '%s'\nStackTrace: %s",sql,e.toString());
        }
            stmtQuery.close();
            conn.close();
            return rsUserData;
    }
}
