package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Integer.MAX_VALUE;

class Main {

    static int N, M, X;
    static ArrayList<Node>[] graph;
    static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<Node>();
        }

        dp = new int[N +1][N +1];

        for(int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, weight));
        }

        floyd();

        int max = Integer.MIN_VALUE;
        for(int i=1;i<=N;i++){
            if(i == X) continue;
            max = Math.max((dp[X][i] + dp[i][X]), max);
        }
        System.out.println(max);
    }

    private static void floyd() {
        // dp[?][i][j] : ?번 노드 포함시켰을때. 0번은 그냥 원래꺼 복사.
        for(int i = 0; i< N +1; i++){
            for(int j = 0; j< N +1; j++){
                dp[i][j] = MAX_VALUE;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for(int j=0;j<graph[i].size();j++){
                Node n = graph[i].get(j);
                dp[i][n.to] = n.weight;
            }
        }

        // 1번 노드부터 n번 노드까지 포함시키기
        for(int k = 1; k<= N; k++){
            // 플로이드에서의 모든 노드의 모든 노드간의 가중치 업데이트를 해줄 필요가 없고,
            // 현재 노드 -> (현재까지 고려된 각 노드)까지의 최단 거리 + 파티까지의 최종 거리

            //추가한 노드까지의 최단거리 업데이트

            // 각 노드의 X까지의 거리 업데이트
            for(int i = 1; i<= N; i++){
                if(i == X) continue;
                if(dp[i][k] != MAX_VALUE){
                    for(int j=0;j<graph[k].size();j++){
                        Node n = graph[k].get(j);
                        if(n.to == i) continue;
                        dp[i][n.to] = Math.min(dp[i][n.to], dp[i][k] + n.weight);
                    }
                }

                int weightByPassingNodeK = (dp[i][k] == MAX_VALUE) || (dp[k][X] == MAX_VALUE) ?
                        MAX_VALUE :
                        dp[i][k] + dp[k][X];
                dp[i][X] = Math.min(dp[i][X], weightByPassingNodeK);
            }

            if(dp[X][k] != MAX_VALUE){
                for(int j=0;j<graph[k].size();j++){
                    Node n = graph[k].get(j);
                    if(n.to == X) continue;
                    dp[X][n.to] = Math.min(dp[X][n.to], dp[X][k] + n.weight);
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