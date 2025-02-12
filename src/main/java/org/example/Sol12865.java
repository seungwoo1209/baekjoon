package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Sol12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N,maxWeight;
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());

        ArrayList<Stuff> list = new ArrayList<>(N);
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Stuff(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        list.sort(Comparator.comparing(Stuff::getWeight));

//        list.forEach(stuff -> System.out.println(stuff.weight + " " + stuff.value));

        // 지금 이거 넣는 게 이득이냐? 를 지금 결정할 수 있나?
        // 3 6 4 8 6 99 면 결국 안 넣는 게 이득이긴 하다.
        // 지금 이걸 넣었을 때의 최댓값이 넣지 않았을 때의 최댓값보다 크다면, 넣는 게 낫다.
        // maxvalue(n) = max(maxvalue(n-1), maxWeight - n 보다 작은 미리 구한 maxvalue + 현재 물건의 가치)
        // 이게 맞을까?
        // 현재보다 나중에 maxWeight - n 보다 작은 미리 구한 maxvalue 보다 더 나은 무게와 가치의 조합이 등장할 순 없을까?
        // 그 maxvalue의 특정 무게보다 현재 구하는 무게가 크거나 같아면, 등장할 수 없을 것이다. 정렬된 리스트였기 때문.
        // 그럼 모든 무게 조합에 대해서 모든 결과를 다 구해야 하나? 시간 제한이 2초긴 하다.
        // 3 6 4 8 5 12 6 13 의 예에서 구할 수 있는 경우는 3, 4, 5 , 6, 3+4, 3+5, 3+6, 4+5, 4+6, 5+6, 3+4+5, ....
        // nC1 + nC2 + nC3 + ..... nCn이다.
        // 그것보다, 하나가 추가됐을 때 가능한 조합의 경우는 몇 개가 추가되는가?
        // 3 4 5 6으로 가능한 조합의 경우를 모두 알고 있다고 하자. 7이 추가되면?
        // 1개 선태: 7 추가 2개 선택: 3+7,4+7,5+7,6+7 추가
        // 선택하는 경우의 수는 7을 반드시 뽑고 나머지를 n-1C1, n-1C2... 하는 경우와 같다.
        // 물품의 수는 100개라고 했다(최대)
        // 그렇다면 100번째 물건이 추가되는 순간 조합의 수는 99C1 + 99C2 + ..... 99C98 + 99C99. 그리고 그 경우를 다 기억해야 한다.
        // 시간제한을 생각해도 너무 많은 것 같다.
        // 근데 일단 무게 제한까지만 생각하면 되긴 한다.
        // 무게제한은 100000 까지. 모든 각 무게에 대해 배열로 치면 32비트 * 100000 = 3,200,000. 3MB라 할만하다.
        // 그럼 무게 제한까지 인덱스가 무게, 값이 가치인 배열을 선언하고 계속 업데이트하면 될 것이다.
        // 지금까지의 배열에서 새로운 무게와 가치가 추가되면 지금의 인덱스 + 새로운 무게 = 지금의 가치 + 새로운 가치 해주면 될 것 같긴 하다.
        // 그럼 우리가 구하는 값은, 주어진 무게 만큼의 인덱스보다 작은 인덱스 중 가장 큰 인덱스의 배열값일 것이다.

        int[] dp = new int[maxWeight + 1];

        for(int i=0;i<N;i++){
            Stuff stuff = list.get(i);
            for(int j=0;j<=maxWeight;j++){
                if(dp[j] != 0){
                    if(j + stuff.weight <= maxWeight){
                        if(dp[j + stuff.weight] < dp[j] + stuff.value){
                            dp[j + stuff.weight] = dp[j] + stuff.value;
                        }
                    }
                }
            }
            if(stuff.weight <= maxWeight){
                if(dp[stuff.weight] < stuff.value)
                    dp[stuff.weight] = stuff.value;
            }
        }

        int max = 0;
        for(int i=maxWeight;i>=0;i--){
            if(dp[i] > max){
                max = dp[i];
            }
        }

        System.out.println(max);

    }
}

class Stuff {
    public int weight;
    public int value;

    public Stuff(int weight, int value){
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}