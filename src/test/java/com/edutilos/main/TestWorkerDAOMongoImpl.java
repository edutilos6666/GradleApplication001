package com.edutilos.main;


import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestWorkerDAOMongoImpl extends TestCase {

    WorkerDAO dao ;

   @Before
    public void setUp() {
      dao = new WorkerDAOMongoImpl();
       ((WorkerDAOMongoImpl)dao).dropCollection();
       dao.save(new Worker(1, "foo", 10, 100.0));
       dao.save(new Worker(2, "bar", 20, 200.0));
       dao.save(new Worker(3, "bim", 30, 300.0));
   }


    @After
    public void tearDown() {

    }



    @Test
    public void test1() {
        List<Worker> all = dao.findAll();
        assertEquals(3, all.size());

        Worker w = dao.findById(1);
        assertEquals(1, w.id);
        assertEquals("foo", w.name);
        assertEquals(10, w.age);
        assertEquals(100.0, w.wage);
    }

    @Test
    public void test2() {
        dao.update(1, new Worker(1, "newfoo", 66, 666.6));
        Worker w = dao.findById(1);
        assertEquals(1, w.id);
        assertEquals("newfoo", w.name);
        assertEquals(66, w.age);
        assertEquals(666.6, w.wage, 0.0);
    }


     @Test
    public void test3() {
         dao.remove(1);
         List<Worker> all = dao.findAll();
         assertEquals(2, all.size());
     }


}
