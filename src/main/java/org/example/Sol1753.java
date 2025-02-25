package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        List<HashMap<Integer, Integer>> mapList = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            mapList.add(new HashMap<>());
        }

        int start, end, weight;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            mapList.get(start).merge(end, weight,
                    (existingWeight, newWeight) ->
                            (newWeight < existingWeight) ? newWeight : existingWeight
            );
        }

        PriorityQueue<Node1753> queue = new PriorityQueue<>();
        int[] distance = new int[V+1];
        for(int i=0;i<V+1;i++){
            distance[i] = -1;
        }
        distance[K] = 0;
        queue.add(new Node1753(K,0));

        while(!queue.isEmpty()){
            Node1753 from = queue.remove();

            mapList.get(from.vertex).forEach((to, dist) -> {
                if(distance[to] != -1 && distance[from.vertex] >= distance[to]){

                } else {
                    // 화살표 뻗은데가 초기화 안됐거나, 가는 게 이득이면
                    if((distance[to] == -1 || distance[from.vertex] + dist < distance[to])){
                        distance[to] = distance[from.vertex] + dist;
                        queue.add(new Node1753(to,dist));
                    }
                }
            });
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<V;i++){
            if(distance[i+1] == -1) {
                sb.append("INF");
            } else {
                sb.append(distance[i+1]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

class Node1753 implements Comparable<Node1753> {
    int vertex;
    int distance;
    public Node1753(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }
    public int compareTo(Node1753 other) {
        return Integer.compare(this.distance, other.distance);
    }
}
