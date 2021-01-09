package com.test.interview;

import com.test.interview.models.models.Record;
import com.test.interview.models.models.RecordDeque;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    int size;
    ConcurrentHashMap<Integer, Record> map;
    RecordDeque recordDeque;

    public Cache(int size) {
        this.size = size;
        this.map = new ConcurrentHashMap<>();
        this.recordDeque = new RecordDeque();
    }

    public synchronized Integer get(int key){
        Record record = map.get(key);
        long currTime = System.currentTimeMillis() * 1000;

        if (record == null)
            return null;
        if(record.getLoadTime()+ record.getExpiryTime() < currTime) {
            //Clean the cache up
            return null;
        }
        else {
            record.setLoadTime(currTime);
            return record.getValue();
        }

    }

    public synchronized void put(int key, int value, int expiryTime){
        Record record = map.get(key);

        long currTime = System.currentTimeMillis() * 1000;

        if(record!=null){
            record.setLoadTime(currTime);
            record.setValue(value);
            record.setExpiryTime(expiryTime);

            recordDeque.removeAndAddToHead(record);
            return;
        }

        record = new Record(key, value, currTime, expiryTime);

        if(map.size() == size)
            evictFromCache();

        map.put(key, record);
        recordDeque.addToHead(record);

    }

    private void evictFromCache() {
        Record record = recordDeque.deleteTail();
        map.remove(record.getKey());
    }
}
