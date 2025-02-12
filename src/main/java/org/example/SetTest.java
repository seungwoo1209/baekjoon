package org.example;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

    static Set<Edge> set;

    public static void main(String[] args) {
        set = new HashSet<>();
        set.add(Edge.of(1,2));
        set.add(Edge.of(1,2));
        set.add(Edge.of(1,2));
        System.out.println("set = " + set);
    }

}

@AllArgsConstructor
class Edge {
    int v1;
    int v2;

    public static Edge of(int v1, int v2){
        return new Edge(v1,v2);
    }
}