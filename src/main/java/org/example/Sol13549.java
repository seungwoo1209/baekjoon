package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
        } else if (N > K) {
            System.out.println(N - K);
        } else {

            int[] arr = new int[1000001];
            for (int i = 0; i < 1000001; i++) {
                arr[i] = -1;
            }
            arr[N] = 0;

            ArrayList<Integer> startingPoint = new ArrayList<>();
            startingPoint.add(N);
            boolean finished = false;

            while (!finished) {
                ArrayList<Integer> copy = (ArrayList<Integer>) startingPoint.clone();
                startingPoint.clear();

                finished = true;
                for (int n : copy) {

                    // 지금꺼보다 이미 더 빠른 해답이 있다면
                    if(arr[K] != -1 && arr[n] >= arr[K]){
                        continue;
                    }

                    if (n - 1 >= 0) {
                        // 가본 적 없거나 가는 게 이득이면
                        if (arr[n - 1] < 0 || arr[n - 1] > arr[n] + 1) {
                            arr[n - 1] = arr[n] + 1;
                            startingPoint.add(n - 1);
                            finished = false;
                        }
                    }

                    if (n + 1 <= 100000) {
                        if (arr[n + 1] < 0 || arr[n + 1] > arr[n] + 1) {
                            arr[n + 1] = arr[n] + 1;
                            startingPoint.add(n + 1);
                            finished = false;
                        }
                    }

                    if (n != 0 && n * 2 <= 100000) {
                        // 이미 답까지 어떤 방식으로 갔는데, 지금 *2 한거가 그거보다 무조건 오래걸리면
                        // 이 방식은 시도하지 않는다.
                        if(arr[K] == -1 || n*2 - K < arr[K]){
                            arr[n * 2] = arr[n];
                            startingPoint.add(n * 2);
                            finished = false;
                        }
                    }
                }
            }
            System.out.println(arr[K]);
        }

    }
}
/*
         모든 경우의 수를 계산하지 않고, 일반적인 해법을 얻을 수 있을까?
         5 17의 경우에는, 5 10 9 18 17. 이렇게 해서 2초이다.
         5 10 20 19 18 17의 경우에는 3초, 5 4 8 16 17의 경우에는 2초이다.
         이 경우로 목표한 수에 가까워지게 행동하는 것이, 항상 더 나은 선택은 아니라는 것을 알았다.
         근데 하나 확실한 것은, 목표보다 현재 작은 수면 -1, +1이나 *2가 둘 다 나은 선택일 수 있지만,
         목표보다 현재 큰 수면 *2나 +1는 절대 더 나은 선택이 될 수 없다는 것.
         어찌저찌 -1, +1, *2를 통해 목표보다 큰 수를 만들었다고 하자.
         그 상태에서부터의 최선의 선택은 무조건 목표까지 -1의 반복이다.
         목표보다 작은 수라면, 무엇이 최선의 선택인지는 알 수 없다.

         53이 목표라고 해보자.
         53 -> 54 or 52 -> 27 or 26 -> 26 or 28 -> 13 or 14 ->...
         53보다 큰 무언가가 출발점이면, 시간은 ? - 53 일 것이다.
         52, 26, 13은 1, 51은? 2. 50은? 25*2, 따라서 2. 경우가 너무 많아..

         5 -> 4, 6, 10

         4 -> 3, 8
         6 -> 7, 12
         10 -> 9, 11, 20

         3 -> 2
         8 -> 16

         7 -> 14
         12 -> 13, 24
         9 -> 18. 끝

         이렇게 하면 될 것 같다.
         */