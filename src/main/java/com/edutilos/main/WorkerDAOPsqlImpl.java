package com.edutilos.main;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAOPsqlImpl implements  WorkerDAO{
  //connection properties
    private final String DRIVER_NAME = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private final String USERNAME = "edutilos";
    private final String PASSWORD = "";
    private final String TABLE_NAME = "PostgreWorker";

    //variables
    private Connection conn ;
    private Statement stmt;
    private PreparedStatement pstmt ;
    private ResultSet rs ;


    public void connect() throws Exception {
        Class.forName(DRIVER_NAME);
        conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        stmt = conn.createStatement();
    }

    public void disconnect() throws Exception {
      if(rs != null) rs.close();
        if(pstmt != null) pstmt.close();
        if(stmt != null) stmt.close();
        if(conn != null) conn.close();
    }

    public void dropTable() {
        try {
            connect();
            String cmd = "drop table if exists " + TABLE_NAME;
            System.out.println(stmt.execute(cmd));
            disconnect();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void createTable() {
        try {
            connect();
            String cmd = "create table if not exists " + TABLE_NAME + "(" +
                    "id bigint primary key , " +
                    "name varchar(50) not null , " +
                    "age int not null, " +
                    "wage real not null" +
                    ")";
            System.out.println(stmt.execute(cmd));
            disconnect();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public void save(Worker worker) {
        try {
            connect();
            String cmd = "insert into " + TABLE_NAME + " VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(cmd);
            pstmt.setLong(1, worker.id);
            pstmt.setString(2, worker.name);
            pstmt.setInt(3, worker.age);
            pstmt.setDouble(4, worker.wage);
            System.out.println(pstmt.execute());
            disconnect();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(long id, Worker newWorker) {
        try {
            connect();
            String cmd = "update "+ TABLE_NAME + " set name = ?, age = ?, wage = ? where id = ?";
            pstmt = conn.prepareStatement(cmd);
            pstmt.setString(1, newWorker.name);
            pstmt.setInt(2, newWorker.age);
            pstmt.setDouble(3, newWorker.wage);
            pstmt.setLong(4, id);
            System.out.println(pstmt.execute());
            disconnect();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void remove(long id) {
        try {
            connect();
           String cmd = "delete from " + TABLE_NAME  + " where id = ?";
            pstmt = conn.prepareStatement(cmd);
            pstmt.setLong(1, id);
            System.out.println(pstmt.execute());
            disconnect();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Worker findById(long id) {
        Worker worker = new Worker();
        try {
            connect();
            String cmd = "select * from " + TABLE_NAME + " where id = ?";
            pstmt = conn.prepareStatement(cmd);
            pstmt.setLong(1 , id);
            rs = pstmt.executeQuery();
            if(!rs.next()) return worker ;
            worker = WorkerMapper.mapRSToWorker(rs);
            disconnect();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        return worker;
    }

    @Override
    public List<Worker> findAll() {
        List<Worker> list = new ArrayList<>();
        try {
            connect();
            String cmd = "select * from "+ TABLE_NAME ;
            rs = stmt.executeQuery(cmd);
            while(rs.next()) {
                Worker w = WorkerMapper.mapRSToWorker(rs);
                list.add(w);
            }
            disconnect();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
