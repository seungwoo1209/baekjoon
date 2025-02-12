package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] dp = new int[2][n];
        for(int i=0;i<2;i++){
            for(int j=0;j<n;j++){
                dp[i][j] = 0;
            }
        }

        for(int i=0;i<n;i++){
            int[] arr = new int[i+1];
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<=i;j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            for(int k=0;k<=i;k++){
                if(k == 0){
                    dp[i%2][k] = dp[(i+1)%2][k] + arr[k];
                } else if (k == i){
                    dp[i%2][k] = dp[(i+1)%2][k-1] + arr[k];
                } else {
                    dp[i%2][k] = Math.max(dp[(i+1)%2][k-1] + arr[k], dp[(i+1)%2][k] + arr[k]);
                }
            }
        }

        int max = Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            if(dp[(n-1) % 2][i] > max){
                max = dp[(n-1) % 2][i];
            }
        }
        System.out.println(max);

    }
}
