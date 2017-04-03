package com.edutilos.main;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class AddWorkerGUI extends JFrame {
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
    JButton btnAdd, btnCancel;
    //dao and jtable
    WorkerDAO dao ;
    JTable tableWorker;

    public AddWorkerGUI(WorkerDAO dao, JTable tableWorker) {
        this.dao = dao;
        this.tableWorker = tableWorker;
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
        btnAdd = new JButton("Add");
        btnCancel = new JButton("Cancel");
        panelMain.add(btnAdd);
        panelMain.add(btnCancel);
    }

    public void registerEvents() {
        btnAdd.addActionListener(l -> {
            String idStr = fieldId.getText(),
                    nameStr = fieldName.getText(),
                    ageStr = fieldAge.getText(),
                    wageStr = fieldWage.getText();
            java.util.List<String> fields = Arrays.asList(idStr, nameStr, ageStr, wageStr);
            boolean verifyRes = WorkerUtils.verifyFields(fields);
            if(verifyRes) {
                long id = Long.parseLong(idStr);
                java.util.List<Worker> all = dao.findAll();
                boolean duplicate = false ;
                for(Worker w: all) {
                   if(w.id == id) {
                       JOptionPane.showMessageDialog(AddWorkerGUI.this, "Id already exists");
                       duplicate = true ;
                       break;
                   }
                }

                if(!duplicate) {
                    int age = Integer.parseInt(ageStr);
                    double wage = Double.parseDouble(wageStr);
                    Worker w = new Worker(id, nameStr, age, wage);
                    dao.save(w);
                    WorkerMapper.refillTableModel((DefaultTableModel) tableWorker.getModel(), dao);
                    AddWorkerGUI.this.dispose();
                }
            } else {
               JOptionPane.showMessageDialog(AddWorkerGUI.this, "Invalid input");
            }
        });

        btnCancel.addActionListener(l-> {
              fieldId.setText("");
            fieldName.setText("");
            fieldAge.setText("");
            fieldWage.setText("");
        });
    }
}
