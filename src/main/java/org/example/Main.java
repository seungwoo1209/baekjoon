package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<TC;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            HashMap<Integer, Integer>[] graph = new HashMap[N+1];
            for(int j=0;j<=N;j++){
                graph[j] = new HashMap<>();
            }

            for(int j=1;j<=N;j++){
                graph[0].put(j,0);
            }

            for(int j=0;j<M;j++){
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[v1].merge(v2,weight,Math::min);
                graph[v2].merge(v1,weight,Math::min);
            }

            for(int j=0;j<W;j++){
                st = new StringTokenizer(br.readLine());
                int from  = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[from].merge(to,-1*weight,Math::min);
            }

            String ans = bellmanFord(graph,N,0) ? "YES" : "NO";
            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }

    static boolean bellmanFord(HashMap<Integer,Integer>[] graph,int N, int start){
        //정점의 가중치를 초기화한다.
        //모든 간선들에 대하여, (start, end, weight)
        //시작점에서 start까지의 거리와 start부터 end까지의 거리의 합이
        //시작점에서부터 end까지의 거리보다 짧다면
        //가중치의 업데이트를 진행한다.
        //1개의 간선을 사용한 각 노드까지의 최단 거리
        //2개의 간선을 사용한 각 노드까지의 최단 거리
        //3개의 간선을 사용한 각 노드까지의 최단 거리
        //.... V-1개의 간선을 사용한 각 노드까지의 최단 거리
        //V-1개의 간선을 사용한 각 노드까지의 최단 거리는 반드시 최단 거리임.
        int[] dist = new int[N+1];
        for(int i=1;i<=N;i++){
            dist[i] = MAX_VALUE;
        }

        dist[start] = 0;
        for(int i=0;i<=N-1;i++){
            for(int j=0;j<=N;j++){
                if(dist[j] == MAX_VALUE) continue;
                // 시작점에서 j번 노드로 갈 수 있는 상황
                for (Map.Entry<Integer, Integer> entry : graph[j].entrySet()) {
                    if (dist[j] + entry.getValue() < dist[entry.getKey()]) {
                        dist[entry.getKey()] = dist[j] + entry.getValue();
                    }
                }
            }
        }

        boolean flag = false;
        loop:
        for(int j=0;j<=N;j++){
            if(dist[j] == MAX_VALUE) continue;
            // 시작점에서 j번 노드로 갈 수 있는 상황
            for (Map.Entry<Integer, Integer> entry : graph[j].entrySet()) {
                if (dist[j] + entry.getValue() < dist[entry.getKey()]) {
                    flag = true;
                    break loop;
                }
            }
        }
        return flag;
    }

}


