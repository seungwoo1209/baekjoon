package org.example;

import javax.swing.border.CompoundBorder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N;
    static Long sum = 0L;
    static boolean[] visitedRow;
    static boolean[] visitedCol;
    static boolean[] visitedSum;
    static boolean[] visitedDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Long start = System.nanoTime();
        visitedRow = new boolean[N];
        visitedCol = new boolean[N];
        visitedSum = new boolean[2*N-1];
        visitedDiff = new boolean[2*N-1];
        for (int i = 0; i < N; i++) {
            calc(0, i, 0);
        }

        Long end = System.nanoTime();
        System.out.println(sum);
        System.out.println((double)(end - start) / (1000 * 1000));
    }

    static void calc(int rowindex, int colindex, int currentQueenCount) {
        currentQueenCount++;
        if(currentQueenCount == N){
            sum++;
            return;
        }

        // 못가는데 표시하기
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                boolean sameRow = (i == rowindex);
//                boolean sameCol = (j == colindex);
//                boolean sameSum = (i + j == rowindex + colindex);
//                boolean sameDiff = (i - j == rowindex - colindex);
//                if(sameRow || sameCol || sameSum || sameDiff){
//                    if(!visited[i][j]){
//                        visited[i][j] = true;
//                        list.add(new Coord(i, j));
//                    }
//                }
//            }
//        }

        visitedRow[rowindex] = true;
        visitedCol[colindex] = true;
        visitedSum[rowindex + colindex] = true;
        visitedDiff[N-1+rowindex - colindex] = true;

        List<Integer> possibleRow = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(!visitedRow[i]){
                possibleRow.add(i);
            }
        }
        List<Integer> possibleCol = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(!visitedCol[i]){
                possibleCol.add(i);
            }
        }

        if(possibleRow.size() != 0 && possibleCol.size() != 0){
            for(int i = 0; i < possibleRow.size(); i++){
                for(int j = 0; j < possibleCol.size(); j++){
                    int row = possibleRow.get(i);
                    int col = possibleCol.get(j);
                    if(!visitedSum[row + col] && !visitedDiff[N-1 + row - col]){
                        calc(i, j, currentQueenCount);
                    }
                }
            }
        }

        visitedRow[rowindex] = false;
        visitedCol[colindex] = false;
        visitedSum[rowindex + colindex] = false;
        visitedDiff[N-1+rowindex - colindex] = false;

//        // 안간데 딱 하나만 찾기
//        int newRow = -1;
//        int newCol = -1;
//        Loop:
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                if(!visited[i][j]){
//                    newRow = i;
//                    newCol = j;
//                    break Loop;
//                }
//            }
//        }

    }


}

class Coord {
    public int row;
    public int col;
    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }
}