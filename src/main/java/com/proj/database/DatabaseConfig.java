package com.proj.database;

import com.proj.database.SQLRunner;
import java.sql.*;

public class DatabaseConfig {
    private final String databaseURL = "jdbc:sqlite:src/main/database/app.db";
    private final String schemaPath = "src/main/database/schema.sql";

    public DatabaseConfig(){
    }

    public Connection getConn() throws SQLException{
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.databaseURL);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public void initDB(){
        SQLRunner runner = new SQLRunner();
        try(var conn = getConn()){
            runner.execSql(this.schemaPath, getConn());
            if(conn != null){
                var meta = conn.getMetaData();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
