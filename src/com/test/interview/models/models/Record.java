package com.test.interview.models.models;

import java.util.Objects;

public class Record {

    int key;
    int value;
    Record left;
    Record right;
    int loadTime;
    long expiryTime;

    public Record(int key, int value, int loadTime, long expiryTime) {
        this.key = key;
        this.value = value;
        this.loadTime = loadTime;
        this.expiryTime = expiryTime;
        this.left =null;
        this.right = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return key == record.key &&
                value == record.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
