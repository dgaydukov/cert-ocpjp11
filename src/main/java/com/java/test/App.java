package com.java.test;


/**
 * concurrentskiplistmap vs concurrenthashmap
 * https://blogs.oracle.com/javamagazine/creating-a-java-off-heap-in-memory-database
 * java memory model - metaspace
 * oracle partition
 * julu/jing low latency jdk
 * weakhashmap vs hashmap
 * bytebuffer vs byte[]
 */





//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//public class App{
//    public static void main(String[] args) {
//        var list = new SinglyLinkedList();
//        for(int i = 0; i < 3; i++){
//            list.add(i);
//        }
//        System.out.println(list);
//    }
//}
//
//interface LinkedList<T>{
//    int add(T value);
//    T get(int index);
//    void delete(T value);
//}
//
//class SinglyLinkedList{
//    @Data
//    @AllArgsConstructor
//    private static class Node{
//        private int value;
//        private Node next;
//        public Node(Node node){
//            this(node.getValue(), node.getNext());
//        }
//    }
//
//    private int currentIndex;
//    private Node currentNode;
//    private Node firstNode;
//    public int add(int value) {
//        if (currentNode == null) {
//            currentNode = new Node(value, null);
//        } else if (currentNode.getNext() == null) {
//            currentNode.next = new Node(value, null);
//            firstNode = new Node(currentNode);
//            currentNode = currentNode.next;
//        }
//        else {
//            currentNode.next = new Node(value, null);
//            currentNode = currentNode.next;
//        }
//        return ++currentIndex;
//    }
////    public T get(int index) {
////        return null;
////    }
////    public void delete(T value) {
////
////    }
//
//    @Override
//    public String toString(){
//        var node = new Node(firstNode);
//        while (node.getNext() != null){
//            System.out.println("node => " +node);
//            node = node.getNext();
//        }
//        return currentIndex + " => " + currentNode;
//    }
//}
//
//@Data
//class DoublyLinkedList{
//    private int value;
//    private DoublyLinkedList prev;
//    private DoublyLinkedList next;
//}
//
//@Data
//class SkipList{
//    private int value;
//    private SkipList next;
//}