package com.edutilos.main;


import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestWorkerDAOMysqlImpl extends TestCase {
  WorkerDAO dao ;

    @Before
    public void setUp() {
        dao = new WorkerDAOMysqlImpl();
        ((WorkerDAOMysqlImpl)dao).dropTable();
        ((WorkerDAOMysqlImpl)dao).createTable();
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
    }
}
