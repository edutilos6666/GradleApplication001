package com.edutilos.main;


import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestWorkerDAOPsqlImpl extends TestCase {
   WorkerDAO dao ;

    @Before
    public void setUp() {
      dao = new WorkerDAOPsqlImpl();
        ((WorkerDAOPsqlImpl)dao).dropTable();
        ((WorkerDAOPsqlImpl)dao).createTable();
        dao.save(new Worker(1, "foo", 10 , 100.0));
        dao.save(new Worker(2, "bar", 20 , 200.0));
        dao.save(new Worker(3, "bim", 30 , 300.0));
    }

    @After
    public void tearDown() {

    }


    @Test
    public void test1() {
        List<Worker> all = dao.findAll();
        assertEquals(3 , all.size());

        Worker w = dao.findById(1L);
        assertEquals(1L , w.id);
        assertEquals("foo", w.name);
        assertEquals(10, w.age);
        assertEquals(100.0, w.wage, 0.0);
    }


    @Test
    public void test2() {
        dao.update(1L , new Worker(1L, "newfoo", 66, 666.6));
        Worker w = dao.findById(1L);
        System.out.println(w);
        assertEquals(1L, w.id);
        assertEquals("newfoo", w.name);
        assertEquals(66, w.age);
        assertEquals(666.6, w.wage, 0.1);
    }


    @Test
    public void test3() {
        dao.remove(1L);
        List<Worker> all = dao.findAll();
        assertEquals(2, all.size());
    }
}
