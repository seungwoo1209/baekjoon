package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][N];
        dp[0][0] = arr[0][0];
        int sum = 1;
        while (sum <= 2 * (N - 1)) {
            int lim = (N <= sum) ? (sum - N + 1) : 0;

            int i = sum - lim;
            int j = sum - i;
            while(i >= lim){
                if(i == 0){
                    dp[i][j] =  dp[i][j-1] + arr[i][j];
                } else if (i == sum){
                    dp[i][j] = dp[i-1][j] + arr[i][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
                }

                i--;
                j++;
            }
            sum++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int small = (x1 == 0 || y1 == 0) ? 0 : dp[x1-1][y1-1];
            int medium1 = (x1 == 0) ? 0 : dp[x1-1][y2];
            int medium2 = (y1 == 0) ? 0 : dp[x2][y1-1];
            int big = dp[x2][y2];

            sb.append(big - medium1 - medium2 + small);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
