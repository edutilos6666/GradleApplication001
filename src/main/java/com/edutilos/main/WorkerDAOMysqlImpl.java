package com.edutilos.main;


import java.util.ArrayList;
import java.util.List;
import java.sql.* ;
public class WorkerDAOMysqlImpl implements  WorkerDAO{
   //connection properties
    private Connection conn ;
    private Statement stmt ;
    private ResultSet rs ;
    private PreparedStatement pstmt ;
    private final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "";
    public WorkerDAOMysqlImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
     }

    /*
        public long id;
    public String name;
    public int age;
    public double wage ;
     */

    public void dropTable() {
        connect();
        try {
            String cmd = "drop table if exists JavaWorker";
            System.out.println(stmt.execute(cmd));
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();
    }


    public void createTable() {
        connect();
        try {
            String cmd = "create table if not exists JavaWorker (" +
                    "id bigint primary key, " +
                    "name varchar(50) not null, " +
                    "age int not null, " +
                    "wage double not null" +
                    ")";
            System.out.println(stmt.execute(cmd));
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();
    }

    public void disconnect()  {
        try {
            if (rs != null) {
               rs.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }

            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public void save(Worker worker) {
        connect();
        try {
           String cmd = "insert into JavaWorker VALUES(?, ?, ?, ?)";
            pstmt = conn.prepareStatement(cmd);
            pstmt.setLong(1, worker.id);
            pstmt.setString(2, worker.name);
            pstmt.setInt(3, worker.age);
            pstmt.setDouble(4, worker.wage);
            System.out.println(pstmt.execute());
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();
    }

    @Override
    public void update(long id, Worker newWorker) {
        connect();
        try {
         String cmd = "update JavaWorker set name = ? , age = ? , wage = ? where id = ?";
            pstmt = conn.prepareStatement(cmd);
            pstmt.setString(1 , newWorker.name);
            pstmt.setInt(2, newWorker.age);
            pstmt.setDouble(3, newWorker.wage);
            pstmt.setLong(4, id);
            System.out.println(pstmt.execute());
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();
    }

    @Override
    public void remove(long id) {
        connect();
        try {
           String cmd = "delete from JavaWorker where id = ?";
            pstmt = conn.prepareStatement(cmd);
            pstmt.setLong(1, id);
            System.out.println(pstmt.execute());
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();
    }

    @Override
    public Worker findById(long id) {
        Worker w  = new Worker();
        connect();
        try {
           String cmd = "select * from JavaWorker where id  = ?";
            pstmt = conn.prepareStatement(cmd);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(!rs.next()) return w;
            w = WorkerMapper.mapRSToWorker(rs);
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();
        return w;
    }

    @Override
    public List<Worker> findAll() {
        List<Worker> list = new ArrayList<>();
        connect();
        try {
          String cmd = "select * from JavaWorker";
          rs = stmt.executeQuery(cmd);
            while(rs.next()) {
                list.add(WorkerMapper.mapRSToWorker(rs));
            }
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();

    //    System.out.println(list.toString());
        return list;
    }
}
