package com.test.interview.models.models;

import java.util.Objects;

public class Record {

    int key;
    int value;
    Record left;
    Record right;
    long loadTime;
    long expiryTime;

    public Record(int key, int value, long loadTime, long expiryTime) {
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

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
}
