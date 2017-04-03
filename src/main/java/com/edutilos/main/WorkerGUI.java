package com.edutilos.main;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.*;

public class WorkerGUI extends JFrame {
 //properties
    private JPanel panelMain;
    private BoxLayout layoutMain;
    private JLabel lblTitle;
    private JTable tableWorker ;
    private DefaultTableModel modelWorker;
    private JButton btnAdd , btnUpdate , btnDetails, btnRemove ;
    private Vector<String> columnNames ;
    private WorkerDAO dao ;

    public WorkerGUI(WorkerDAO dao) {
        this.dao = dao ;
        configureWindow();
    }

    public void configureWindow() {
        this.setSize(new Dimension(500, 600));
        this.setTitle("Simple Worker Form Example");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents();
        registerEvents();
    }

    public void addComponents() {
        panelMain = new JPanel();
        this.getContentPane().add(panelMain);
        layoutMain = new BoxLayout(panelMain , BoxLayout.Y_AXIS);
        panelMain.setLayout(layoutMain);
        lblTitle = new JLabel("Worker list");
        panelMain.add(lblTitle);
        //table

        columnNames = new Vector<>();
        columnNames.add("Id");
        columnNames.add("Name");
        columnNames.add("Age");
        columnNames.add("Wage");

        java.util.List<Worker> all = dao.findAll();
        Vector<Vector<Object>> data = WorkerMapper.mapListToWorker(all);
        System.out.println(data.size());
        modelWorker = new DefaultTableModel(data, columnNames);
        tableWorker = new JTable(modelWorker);
        tableWorker.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane pane = new JScrollPane(tableWorker);
        //port.setView(tableWorker);
        panelMain.add(pane);
        //buttons
        JPanel panelControllers = new JPanel(new FlowLayout());
        btnAdd = new JButton("Add Worker");
        panelControllers.add(btnAdd);
        btnUpdate = new JButton("Update Worker");
        panelControllers.add(btnUpdate);
        btnDetails = new JButton("Worker Details");
        panelControllers.add(btnDetails);
        btnRemove = new JButton("Remove Worker");
        panelControllers.add(btnRemove);
        panelMain.add(panelControllers);
    }

    public void registerEvents() {
         btnAdd.addActionListener(l-> {
             AddWorkerGUI add = new AddWorkerGUI(dao, tableWorker);
             add.setVisible(true);
           //  WorkerMapper.refillTableModel((DefaultTableModel)modelWorker, dao);
         });


        btnUpdate.addActionListener(l -> {
            ListSelectionModel selectionModel = tableWorker.getSelectionModel();
            int min = selectionModel.getMinSelectionIndex();
            int max = selectionModel.getMaxSelectionIndex();
            if(min < 0) return ;
            Vector<Object> row = (Vector<Object>)modelWorker.getDataVector().get(min);
            long id = Long.parseLong(row.get(0).toString());

            UpdateWorkerGUI update = new UpdateWorkerGUI(dao, tableWorker, id);
            update.setVisible(true);
        });

        btnDetails.addActionListener(l -> {
            ListSelectionModel selectionModel = tableWorker.getSelectionModel();
            int min = selectionModel.getMinSelectionIndex();
            int max = selectionModel.getMaxSelectionIndex();
            if(min < 0) return ;
            Vector<Object> row = (Vector<Object>)modelWorker.getDataVector().get(min);
            long id = Long.parseLong(row.get(0).toString());

            WorkerDetailsGUI details = new WorkerDetailsGUI(dao, id);
            details.setVisible(true);
        });

        btnRemove.addActionListener(l -> {
            ListSelectionModel selectionModel = tableWorker.getSelectionModel();
            int min = selectionModel.getMinSelectionIndex();
            int max = selectionModel.getMaxSelectionIndex();
            if(min < 0) return ;
            Vector<Object> row = (Vector<Object>)modelWorker.getDataVector().get(min);
            long id = Long.parseLong(row.get(0).toString());
             dao.remove(id);
             WorkerMapper.refillTableModel(modelWorker, dao);
        });
    }
}
