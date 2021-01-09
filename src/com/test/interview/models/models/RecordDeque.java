package com.test.interview.models.models;

public class RecordDeque<KEY,VALUE> {

    Record<KEY,VALUE> head;
    Record<KEY,VALUE> tail;

    public RecordDeque() {
        this.head = null;
        this.tail = null;
    }

    public void removeAndAddToHead(Record<KEY,VALUE> record){
        deleteRecord(record);
        addToHead(record);
    }
    public void addToHead(Record<KEY,VALUE> record) {

        if(head == null) {
            head = tail = record;
            head.left = null;
            tail.right = null;
        }
        else {
            tail.right = record;
            record.left = tail;
            tail = record;
            tail.right = null;
        }
    }

    public Record<KEY,VALUE> deleteTail() {
        Record<KEY,VALUE> currTail = tail;
        if(head == null) {
             return null;
        }
        if(head != tail) {
            tail = tail.left;
            tail.right = null;
        }
        else {
            head = tail = null;
        }

        return currTail;
    }

    public void deleteHead() {
        if(head == null) {
            return;
        }
        if(head != tail) {
            head = head.right;
            head.left = null;
        }
        else {
            head = tail = null;
        }
    }

    public void deleteRecord(Record<KEY,VALUE> record) {
        if(record == head)
            deleteHead();
        else if(record == tail)
            deleteTail();
        else {
            record.left = record.right;
        }
    }

    public Record<KEY, VALUE> getHead() {
        return head;
    }

    public void setHead(Record<KEY, VALUE> head) {
        this.head = head;
    }

    public Record<KEY, VALUE> getTail() {
        return tail;
    }

    public void setTail(Record<KEY, VALUE> tail) {
        this.tail = tail;
    }
}
