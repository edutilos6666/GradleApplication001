package com.edutilos.main;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WorkerDAOMongoImpl implements WorkerDAO {
    //connection properties
    private final String HOSTNAME = "localhost";
    private final int PORT = 27017 ;
    private final String DB_NAME = "test";
    private final String COLL_NAME = "MongoWorker";
    //variables
    private MongoClient client ;
    private MongoDatabase db ;
    private MongoCollection<Document> workerColl;

    public void connect() {
      client = new MongoClient(HOSTNAME, PORT);
        db = client.getDatabase(DB_NAME);
        workerColl = db.getCollection(COLL_NAME);
    }

    public void disconnect() {
     try {
        if(client != null) client.close();
     } catch(Exception ex) {
         ex.printStackTrace();
     }
    }


    public void dropCollection() {
        connect();
     workerColl.drop();
        disconnect();
    }


    @Override
    public void save(Worker worker) {
        connect();
         Document doc = new Document("id", worker.id)
                 .append("name", worker.name)
                 .append("age", worker.age)
                 .append("wage", worker.wage);
        workerColl.insertOne(doc);
        disconnect();
    }

    @Override
    public void update(long id, Worker newWorker) {
       connect();
        Document filter = new Document("id", id);
        Document replacement = new Document("id", newWorker.id)
                .append("name", newWorker.name)
                .append("age", newWorker.age)
                .append("wage", newWorker.wage);
        workerColl.replaceOne(filter, replacement);
        disconnect();
    }

    @Override
    public void remove(long id) {
        connect();
        Document filter = new Document("id", id);
        workerColl.deleteOne(filter);
        disconnect();
    }

    @Override
    public Worker findById(long id) {
        connect();
        Document filter = new Document("id", id);
        Document found = workerColl.find(filter).first();
        Worker w = WorkerMapper.mapMongoDocumentToWorker(found);
        disconnect();
        return w ;
    }

    @Override
    public List<Worker> findAll() {
        connect();
        List<Worker> all = new ArrayList<>();
        FindIterable<Document> allFound = workerColl.find();
        for(Document doc: allFound) {
            all.add(WorkerMapper.mapMongoDocumentToWorker(doc));
        }

        disconnect();
        return all;
    }
}
