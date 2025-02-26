package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int N;
    static Long sum = 0L;
    static boolean[] visitedRow;
    static boolean[] visitedSum;
    static boolean[] visitedDiff;
    static int currentQueenCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visitedRow = new boolean[N];
        visitedSum = new boolean[2 * N - 1];
        visitedDiff = new boolean[2 * N - 1];

        calc(0);
        System.out.println(sum);
    }

    static void calc(int colindex) {
        if (currentQueenCount == N) {
            sum++;
            return;
        }

        for (int i = 0; i < N; i++) {
            // 못 가는 칸인 경우
            if (visitedRow[i] || visitedSum[i + colindex] || visitedDiff[N - 1 + i - colindex]) continue;
            // 갈수 있는 칸인 경우, 현재 좌표는 row: i, col: colindex
            visitedRow[i] = true;
            visitedSum[i + colindex] = true;
            visitedDiff[N - 1 + i - colindex] = true;
            currentQueenCount++;
            // 해당 칸에 이제 놓은 것. 다음 행으로
            calc(colindex + 1);
            // 놓은 것 취소
            visitedRow[i] = false;
            visitedSum[i + colindex] = false;
            visitedDiff[N - 1 + i - colindex] = false;
            currentQueenCount--;
        }
    }
}
