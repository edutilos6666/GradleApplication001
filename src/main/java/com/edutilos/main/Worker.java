package com.edutilos.main;

/**
 * Created by edutilos on 03/04/2017.
 */
public  class Worker {
    public long id;
    public String name;
    public int age;
    public double wage ;

    public Worker(long id, String name, int age, double wage) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.wage = wage;
    }

    public Worker() {
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", wage=" + wage +
                '}';
    }
}
