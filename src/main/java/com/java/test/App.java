package com.java.test;


import com.koloboke.collect.map.LongObjMap;
import com.koloboke.collect.map.hash.HashLongObjMaps;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // use factory to create desired class
        LongObjMap<Object> map = HashLongObjMaps.getDefaultFactory().newMutableMap();
        map.put(1L, new Object());
        map.put(2L, new Object());
        System.out.println("class=" + map.getClass()+", map=" + map);
        // we can even cast to jdk Map
        Map<Long, Object> castMap = map;
    }
}