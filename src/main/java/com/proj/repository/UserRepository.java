package com.proj.repository;

import com.proj.database.DatabaseConfig;

import java.sql.*;

public class UserRepository {
    private DatabaseConfig dbConfig;
    private Connection conn;

    public UserRepository(){}

    public UserRepository(Connection conn) throws SQLException{
        DatabaseConfig dbConfig = this.dbConfig;
        try{
            conn = dbConfig.getConn();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet fetchUserById(int id) throws SQLException{
        PreparedStatement stmtQuery = null;
        String sql = "Select id, nome, email from tb_user where id = ?";
        ResultSet rsUserData = null;
        try{
            Connection conn = this.dbConfig.getConn();
            stmtQuery = conn.prepareStatement(sql);
            stmtQuery.setInt(1, id);
            rsUserData = stmtQuery.executeQuery();
            conn.close();
        } catch(SQLException e){
            System.out.printf("Erro ao processar query: '%s'\nStackTrace: %s",sql,e.toString());
        }
            return rsUserData;
    }
}
