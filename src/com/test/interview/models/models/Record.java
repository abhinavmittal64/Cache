package com.test.interview.models.models;

import java.util.Objects;

public class Record<KEY,VALUE> {

    KEY key;
    VALUE value;
    Record left;
    Record right;
    long loadTime;
    long expiryTime;

    public Record(KEY key, VALUE value, long loadTime, long expiryTime) {
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

    public KEY getKey() {
        return key;
    }

    public void setKey(KEY key) {
        this.key = key;
    }

    public VALUE getValue() {
        return value;
    }

    public void setValue(VALUE value) {
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

    public Record getLeft() {
        return left;
    }

    public void setLeft(Record left) {
        this.left = left;
    }

    public Record getRight() {
        return right;
    }

    public void setRight(Record right) {
        this.right = right;
    }
}
