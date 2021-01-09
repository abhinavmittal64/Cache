package com.test.interview;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
        Cache<Integer,Integer> c = new Cache(5);
        c.put(1,1,10);
        c.put(2,2,10);
        c.put(3,3,10);
        c.put(4,4,10);
        getKey(1, c);
        c.put(1,11,10);
        Thread.sleep(11000);
        getKey(1, c);
        c.put(5,5,10);
        c.put(6, 6, 10);
        getKey(1, c);
        getKey(2, c);
    }

    private static void getKey(int key, Cache<Integer,Integer> c){
        Integer result = c.get(key);
        if(result == null)
            System.out.println("Result is null");
        else
            System.out.println(result);
    }
}
