package com.test.interview.models.models;

public class RecordDeque {

    Record head;
    Record tail;

    public RecordDeque() {
        this.head = null;
        this.tail = null;
    }

    public void removeAndAddToHead(Record record){
        deleteRecord(record);
        addToHead(record);
    }
    public void addToHead(Record record) {

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

    public Record deleteTail() {
        Record currTail = tail;
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

    public void deleteRecord(Record record) {
        if(record == head)
            deleteHead();
        else if(record == tail)
            deleteTail();
        else {
            record.left = record.right;
        }
    }
}
