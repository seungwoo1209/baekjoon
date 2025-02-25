package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static ArrayList<node>[] arr;
    static Long max = 0L;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            visited[i] = false;
        }

        arr = new ArrayList[n+1];
        for (int i = 0; i < n + 1; i++) {
            arr[i] = new ArrayList<node>();
        }

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child;
            while((child = Integer.parseInt(st.nextToken()))!=-1){
                int weight = Integer.parseInt(st.nextToken());
                arr[parent].add(new node(child,weight));
            }
        }

        Long calc = dfs(1);
        if(max < calc){
            max = calc;
        }

        System.out.println(max);

    }

    private static Long dfs(int i) {
        visited[i] = true;
        ArrayList<Long> results = new ArrayList<>();
        for(int j=0;j<arr[i].size();j++){
            node n = arr[i].get(j);
            if(!visited[n.child]){
                results.add(dfs(n.child) + n.weight);
            }
        }

        if(results.isEmpty()){
            // 자식 없으면 0
           return 0L;
        } else if(results.size() == 1){
            // 자식 하나면 그거
            return results.get(0);
        } else {
            // 여기서 자식 두개로 경로를 형성하는 것이 최댓값이라면
            Collections.sort(results, Collections.reverseOrder());
            Long currMax = results.get(0) + results.get(1);
            if(max < currMax){
                max = currMax;
            }

            // 자식 중에 제일 큰거
            return results.get(0);
        }


    }

}

class node {
    public int child;
    public int weight;

    public node(int child, int weight) {
        this.child = child;
        this.weight = weight;
    }
}


