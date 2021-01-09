package com.test.interview;

import com.test.interview.models.models.Record;
import com.test.interview.models.models.RecordDeque;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<KEY,VALUE> {

    int size;
    ConcurrentHashMap<KEY, Record<KEY,VALUE>> map;
    RecordDeque<KEY,VALUE> recordDeque;

    public Cache(int size) {
        this.size = size;
        this.map = new ConcurrentHashMap<>();
        this.recordDeque = new RecordDeque();
    }

    public synchronized VALUE get(KEY key){
        Record<KEY,VALUE> record = map.get(key);
        long currTime = System.currentTimeMillis() / 1000;

        if (record == null)
            return null;
        if(record.getLoadTime()+ record.getExpiryTime() < currTime) {
            //Clean the cache up. Can be done asynchronously.
            cleanUpCache();
            return null;
        }
        else {
            record.setLoadTime(currTime);
            return record.getValue();
        }

    }

    public synchronized void put(KEY key, VALUE value, int expiryTime){
        Record<KEY,VALUE> record = map.get(key);

        long currTime = System.currentTimeMillis() / 1000;

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
        Record<KEY,VALUE> record = recordDeque.deleteTail();
        map.remove(record.getKey());
    }

    public void cleanUpCache() {
        long currTime = System.currentTimeMillis() / 1000;
        Record<KEY,VALUE> tail = recordDeque.getTail();
        while(tail!=null){
            if(tail.getLoadTime() + tail.getExpiryTime() < currTime)
                recordDeque.deleteRecord(tail);
            tail = tail.getLeft();
        }
    }
}
