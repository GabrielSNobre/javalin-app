package com.proj.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class SQLRunner {
    public void execSql(String schemaPath) throws Exception{
        DatabaseConfig db = new DatabaseConfig();
        try(BufferedReader bf = new BufferedReader(new FileReader(schemaPath));
            Connection conn = db.getConn();
            Statement stmt = conn.createStatement();
        ){
            String line;
            StringBuilder sql = new StringBuilder();
            while((line = bf.readLine()) != null){
                line = line.trim();
                if(line.isEmpty() || line.startsWith("--")){
                    continue;
                }

                sql.append(line).append("\n");

                if(line.endsWith(";")){
                    String query = sql.toString().trim();
                    stmt.execute(query);
                    sql.setLength(0);
                    System.out.println("Executou a linha");
                }
            }
            bf.close();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

}
