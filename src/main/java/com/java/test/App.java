package com.java.test;

public class App{
    public static void main(String[] args) {
        int x = 5, y = 4;
        System.out.println("x => " + x + " / " + Integer.toBinaryString(x));
        System.out.println("y => " + y + " / " + Integer.toBinaryString(y));
        System.out.println("~x => " + ~x + " / " + Integer.toBinaryString(~x));
        System.out.println("x>>1 => " + (x>>1) + " / " + Integer.toBinaryString(x>>1));
        System.out.println("x<<1 => " + (x<<1) + " / " + Integer.toBinaryString(x<<1));
        System.out.println("x&y => " + (x&y) + " / " + Integer.toBinaryString(x&y));
        System.out.println("x^y => " + (x^y) + " / " + Integer.toBinaryString(x^y));
        System.out.println("x|y => " + (x|y) + " / " + Integer.toBinaryString(x|y));
    }
}

interface SimpleQueue<T>{
    boolean offer(T t);
    T poll();
}
class ArraySimpleQueue<T> implements SimpleQueue<T>{
    private T[] buffer;
    private final int capacity;
    private int head = 0;
    private int tail = 0;

    public ArraySimpleQueue(int capacity){
        this.capacity = capacity;
        buffer = (T[]) new Object[capacity];
    }

    int resizeNumber;
    public int getArraySize(){
        return buffer.length;
    }
    public int getResizeNumber(){
        return resizeNumber;
    }

    /**
     * if array is full, create new array, change tail/head values
     */
    @Override
    public boolean offer(T t) {
        if (t == null){
            return false;
        }
        if(buffer[tail] != null){
            //array is full, re-create
            T[] newBuffer = (T[]) new Object[buffer.length + capacity];
            int i = 0, j = head;
            while (buffer[j] != null){
                newBuffer[i++] = buffer[j];
                buffer[j] = null;
                j++;
                if (j == buffer.length){
                    j = 0;
                }
            }
            buffer = newBuffer;
            tail = i;
            head = 0;
        }
        buffer[tail] = t;
        tail++;
        if (tail == buffer.length){
            tail = 0;
        }
        return true;
    }

    @Override
    public T poll() {
        T obj = buffer[head];
        if(obj != null){
            buffer[head] = null;
            head++;
            if(head == buffer.length){
                head = 0;
            }
        }
        return obj;
    }
}