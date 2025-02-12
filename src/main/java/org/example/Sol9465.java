package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][n];
            for(int j=0;j<2;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k=0;k<n;k++){
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] dp = new int[2][n];
            dp[0][0] = arr[0][0]; dp[1][0] = arr[1][0];
            if(n >= 2){
                dp[0][1] = dp[1][0] + arr[0][1];
                dp[1][1] = dp[0][0] + arr[1][1];
            }

            for(int j=2;j<n;j++){
                dp[0][j] = Math.max(dp[1][j-2] + arr[0][j], dp[1][j-1] + arr[0][j]);
                dp[1][j] = Math.max(dp[0][j-2] + arr[1][j], dp[0][j-1] + arr[1][j]);
            }

            System.out.println(Math.max(dp[0][n-1], dp[1][n-1]));
        }
    }
}
