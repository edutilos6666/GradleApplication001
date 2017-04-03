package com.edutilos.main;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class WorkerDetailsGUI extends JFrame {
     //properties
    JPanel panelMain ;
    GridLayout layoutMain ;
    //title
    JLabel lblTitle;
    //id
    JLabel lblId;
    JTextField fieldId ;
    //name
    JLabel lblName ;
    JTextField fieldName;
    //age
    JLabel lblAge ;
    JTextField fieldAge;
    //wage
    JLabel lblWage;
    JTextField fieldWage;
    //buttons
    JButton btnClose;
    //dao and jtable
    WorkerDAO dao ;
    long id ;

    public WorkerDetailsGUI(WorkerDAO dao, long id) {
        this.dao = dao;
        this.id = id ;
        configureWindow();
    }

    public void configureWindow() {
        this.setSize(new Dimension(500, 500));
        this.setTitle("Add New Worker");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         addComponents();
        registerEvents();
    }

    public void addComponents() {
     panelMain = new JPanel();
     this.getContentPane().add(panelMain);
        //layout
        layoutMain = new GridLayout(6, 2);
        panelMain.setLayout(layoutMain);
        //title
        lblTitle = new JLabel("Add New Worker");
        panelMain.add(lblTitle);
        panelMain.add(new JLabel());
        //id
        lblId = new JLabel("Id: ");
        fieldId = new JTextField();
        panelMain.add(lblId);
        panelMain.add(fieldId);
        //name
        lblName = new JLabel("Name: ");
        fieldName = new JTextField();
        panelMain.add(lblName);
        panelMain.add(fieldName);
        //age
        lblAge = new JLabel("Age: ");
        fieldAge = new JTextField();
        panelMain.add(lblAge);
        panelMain.add(fieldAge);
        //wage
        lblWage = new JLabel("Wage: ");
        fieldWage =new JTextField();
        panelMain.add(lblWage);
        panelMain.add(fieldWage);
        //buttons
        btnClose = new JButton("Close");
        panelMain.add(new JLabel());
        panelMain.add(btnClose);



        fieldId.setEnabled(false);
        fieldName.setEnabled(false);
        fieldAge.setEnabled(false);
        fieldWage.setEnabled(false);
        Worker w = dao.findById(id);
        fieldId.setText(w.id+ "");
        fieldName.setText(w.name);
        fieldAge.setText(w.age+ "");
        fieldWage.setText(w.wage+ "");
    }

    public void registerEvents() {
        btnClose.addActionListener(l -> {
             WorkerDetailsGUI.this.dispose();
        });


    }
}
