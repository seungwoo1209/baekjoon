package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Integer.MAX_VALUE;

class Main {

    static int N, M, X;
    static ArrayList<Node>[] graph;
    static ArrayList<Node>[] reverse;
    static int[] dp;
    static int[] dp2;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        reverse = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<Node>();
            reverse[i] = new ArrayList<Node>();
        }

        dp = new int[N +1];
        dp2 = new int[N+1];

        for(int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, weight));
            reverse[end].add(new Node(start, weight));
        }

        dijkstra(dp,graph);
        dijkstra(dp2,reverse);

        int max = Integer.MIN_VALUE;
        for(int i=1;i<=N;i++){
            if(i == X) continue;
            max = Math.max((dp2[i] + dp[i]), max);
        }
        System.out.println(max);
    }

    // 그냥 다익스트라
    private static void dijkstra(int[] arr, ArrayList<Node>[] list) {
        for(int i = 1; i<= N; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        arr[X] = 0;

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            list[i].sort(new Comparator<Node>() {

                @Override
                public int compare(Node o1, Node o2) {
                    return o1.weight - o2.weight;
                }
            });
        }

        deque.add(X);

        while(!deque.isEmpty()){
            int rm = deque.removeFirst();
            for(int i=0;i< list[rm].size();i++){
                Node n = list[rm].get(i);
                if(arr[rm] + n.weight < arr[n.to]){
                    arr[n.to] = arr[rm] + n.weight;
                    deque.add(n.to);
                }
            }
        }

    }
}


class Node {
    public int to;
    public int weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}