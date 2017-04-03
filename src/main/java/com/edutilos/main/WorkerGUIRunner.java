package com.edutilos.main;


public class WorkerGUIRunner {
    public static void main(String[] args) {
        WorkerDAO dao = new WorkerDAOMysqlImpl();
        dao.save(new Worker(1, "foo", 10 , 100.0));
        dao.save(new Worker(2, "bar", 20 , 200.0));
        dao.save(new Worker(3, "bim", 30 , 300.0));
        WorkerGUI gui = new WorkerGUI(dao);
        gui.setVisible(true);
    }

}
