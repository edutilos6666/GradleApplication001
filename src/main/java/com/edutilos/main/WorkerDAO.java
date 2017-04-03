package com.edutilos.main;

import java.util.List;

/**
 * Created by edutilos on 03/04/2017.
 */
public  interface  WorkerDAO {
    void save(Worker worker);
    void update(long id , Worker newWorker);
    void remove(long id);
    Worker findById(long id);
    List<Worker> findAll();
}
