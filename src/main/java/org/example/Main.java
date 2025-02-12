package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static Long A;
    static Long B;
    static Long C;
    static Long reminder;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        reminder = A % C;


        System.out.println(calc(B));

    }

    static Long calc(Long B) {
        if (B == 1) {
            return reminder;
        } else {
            Long temp = calc(B/2);

            if (B % 2 != 0)
                return (temp * temp) % C * reminder % C;
            else
                return (temp * temp) % C;
        }
    }
}

//2147483646 2147483646 2147483647

//        for(int i=0;i<3;i++){
//            for(int j=0;j<N;j++){
//                System.out.print(cost[i][j] + " ");
//            }
//            System.out.println();
//        }