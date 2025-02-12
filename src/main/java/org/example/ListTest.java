package org.example;

import java.util.ArrayList;

public class ListTest {

    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>(5);

        System.out.println("arr = " + arr);
        System.out.println("arr.size() = " + arr.size());


        arr.add(1);
        arr.add(5);
        arr.add(2);
        arr.add(4);
        arr.add(9);
        arr.add(10);


        System.out.println("arr = " + arr);
        System.out.println("arr.size() = " + arr.size());
    }
}
