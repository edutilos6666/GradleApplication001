package com.edutilos.main;


import org.bson.Document;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

public class WorkerMapper {

    public static Worker mapRSToWorker(ResultSet rs) {
        Worker w = new Worker();
        try {
            w.id = rs.getLong(1);
            w.name = rs.getString(2);
            w.age = rs.getInt(3);
            w.wage = rs.getDouble(4);
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }

        return w ;
    }



    public static Worker mapMongoDocumentToWorker(Document doc) {
        Worker w = new Worker();
        w.id = doc.getLong("id");
        w.name = doc.getString("name");
        w.age = doc.getInteger("age");
        w.wage = doc.getDouble("wage");
        return w;
    }


    public static Vector<Vector<Object>> mapListToWorker(List<Worker> list) {
        Vector<Vector<Object>> res = new Vector<>();
        for(Worker w: list) {
         Vector<Object> v = new Vector<>();
            v.add(w.id+"");
            v.add(w.name);
            v.add(w.age+"");
            v.add(w.wage+"");
            res.add(v);
        }

        return res ;
    }


    public static void refillTableModel(DefaultTableModel model, WorkerDAO dao) {
       int rows = model.getDataVector().size();
        for(int i=0; i< rows; ++i) {
            model.removeRow(0);
        }

       // model.fireTableDataChanged();
        List<Worker> all = dao.findAll();
        Vector<Object> colNames = new Vector<>();
        int colCounts = model.getColumnCount();
        for(int i=0 ; i< colCounts; ++i) {
            colNames.add(model.getColumnName(i));
        }
        model.setDataVector(mapListToWorker(all), colNames);
        model.fireTableDataChanged();
    }


 }
