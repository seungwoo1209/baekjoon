package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String firstStr = br.readLine();
        String secondStr = br.readLine();
        String LCS;

        int[][] arr = new int[firstStr.length() + 1][secondStr.length() + 1];

        for(int i=0;i<=firstStr.length();i++){
            arr[i][0] = 0;
        }
        for(int i=0;i<=secondStr.length();i++){
            arr[0][i] = 0;
        }

        for(int i=1;i<= firstStr.length();i++){
            for(int j=1;j<= secondStr.length();j++){

                if(firstStr.charAt(i - 1) == secondStr.charAt(j-1)){
                    arr[i][j] = arr[i-1][j-1] + 1;
                } else {
                    arr[i][j] = Math.max(arr[i][j-1], arr[i-1][j]);
                }

            }
        }
        System.out.println();

        for(int i=0;i<=firstStr.length();i++){
            for(int j=0;j<=secondStr.length();j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        int i=firstStr.length();
        int j=secondStr.length();
        while(i>0 && j > 0){
            if(firstStr.indexOf(i-1) == firstStr.indexOf(j-1)){

            }
            if(arr[i-1][j] == arr[i][j]){
                i--;
                continue;
            }
            if(arr[i][j-1] == arr[i][j]){
                j--;
                continue;
            }


        }
        System.out.println(arr[firstStr.length()][secondStr.length()]);

    }
}
//0 0 0 0 0 0 0
//0 0 1 1 1 1 1
//0 1 1 1 2 2 2
//0 1 2 2 2 3 3
//0 1 2 2 X 3 X
//0 1 2 2 2 X 4
//0 1 2 3 3 X 4