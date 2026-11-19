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
                user.setId(results.getInt("id"));
                user.setNome(results.getString("nome"));
                user.setEmail(results.getString("email"));
                users.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public UserModel fetchUserById(int id) throws SQLException{
        String sql = "Select id, nome, email from tb_user where id = ?";
        UserModel user = new UserModel();
        try(
            Connection conn = this.dbConfig.getConn();
            PreparedStatement stmtQuery = conn.prepareStatement(sql);
        ){
            stmtQuery.setInt(1, id);
            ResultSet results = stmtQuery.executeQuery();
            while(results.next()){
                user.setId(results.getInt("id"));
                user.setNome(results.getString("nome"));
                user.setEmail(results.getString("email"));
            }
        } catch(SQLException e){
            System.out.printf("Erro ao processar query: '%s'\nStackTrace: %s",sql,e.toString());
        }
            return user;
    }

    public void insertUser(UserModel user) throws SQLException{
        String sql = "insert into tb_user(nome,email) values(?,?)";
        try(
            Connection conn = this.dbConfig.getConn();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
            System.out.println("Registro Criado");
        } catch(SQLException e){
            System.out.printf("Erro ao processar query: '%s'\nStackTrace: %s",sql,e.toString());
        }
    }

    public int updateUser(int id, UserModel user) throws SQLException{
        String sql = "update tb_user set nome = ?, email = ? where id = ?";
        int resultados;
        try(
            Connection conn = this.dbConfig.getConn();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,user.getNome());
            stmt.setString(2,user.getEmail());
            stmt.setInt(3, id);
            return resultados = stmt.executeUpdate();
        }

}

    public int deleteUser(int id) throws SQLException{
        String sql = "delete from tb_user where id = ?";
        try(
            Connection conn = this.dbConfig.getConn();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,id);
            return stmt.executeUpdate();
        }
    }
}
