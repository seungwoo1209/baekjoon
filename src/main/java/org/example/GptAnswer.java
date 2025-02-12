package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;

public class GptAnswer {
    static int N, M;
    static int[] arr, result;
    static int[] unique, count;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 빠른 입출력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        // 중복 제거 및 각 수의 등장 횟수 계산
        ArrayList<Integer> uniqueList = new ArrayList<>();
        ArrayList<Integer> countList = new ArrayList<>();

        uniqueList.add(arr[0]);
        countList.add(1);
        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i - 1]) {
                countList.set(countList.size() - 1, countList.get(countList.size() - 1) + 1);
            } else {
                uniqueList.add(arr[i]);
                countList.add(1);
            }
        }
        int size = uniqueList.size();
        unique = new int[size];
        count = new int[size];
        for (int i = 0; i < size; i++) {
            unique[i] = uniqueList.get(i);
            count[i] = countList.get(i);
        }

        result = new int[M];
        backtrack(0);

        // 한 번에 출력
        System.out.print(sb);
    }

    // depth: 현재까지 선택한 원소의 개수
    static void backtrack(int depth) {
        if (depth == M) {
            // result 배열의 값을 StringBuilder에 추가
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // unique 배열의 각 수에 대해 선택
        for (int i = 0; i < unique.length; i++) {
            if (count[i] > 0) {
                result[depth] = unique[i];
                count[i]--;    // 해당 수의 남은 개수 감소
                backtrack(depth + 1);
                count[i]++;    // 재귀 복귀 후 원상 복구
            }
        }
    }
}

