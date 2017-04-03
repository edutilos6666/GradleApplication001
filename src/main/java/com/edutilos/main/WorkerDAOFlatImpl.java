package com.edutilos.main;

import java.util.*;

/**
 * Created by edutilos on 03/04/2017.
 */
public class WorkerDAOFlatImpl implements  WorkerDAO {
    Map<Long , Worker> workerContainer ;

    public WorkerDAOFlatImpl() {
        workerContainer = new HashMap<>();
    }

    @Override
    public void save(Worker worker) {
        workerContainer.put(worker.id , worker);
    }

    @Override
    public void update(long id, Worker newWorker) {
        workerContainer.put(id , newWorker);
    }

    @Override
    public void remove(long id) {
        workerContainer.remove(id);
    }

    @Override
    public Worker findById(long id) {
        Worker w = workerContainer.get(id);
        return w ;
    }

    @Override
    public List<Worker> findAll() {
        List<Worker> res = new ArrayList<>();
         Collection<Worker> coll = workerContainer.values();
         coll.forEach(w-> {
             res.add(w);
         });
        return res;
    }
}