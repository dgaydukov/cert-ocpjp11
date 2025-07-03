package com.java.test;

public class App {
    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.reverse(12));
    }

    int reverse(int n){
        int r = 0;
        while (n > 0) {
            r = (r << 1) | (n & 1);
            n >>= 1;
        }
        return r;
    }
}