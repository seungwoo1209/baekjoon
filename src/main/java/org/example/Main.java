package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {
    static final long divider = 1_000_000_007;
    static long count = 0;
    static HashMap<Long, Long> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long n = Long.parseLong(br.readLine());
        map = new HashMap<>();
        map.put(0L,0L);
        map.put(1L,1L);
        map.put(2L,1L);
        map.put(3L,2L);
        map.put(4L,3L);
        map.put(5L,5L);

        long start = System.nanoTime();
        System.out.println(calc(n));
        long end = System.nanoTime();
//        System.out.println((end - start) / (1000.0 * 1000));
//        System.out.println(count);

    }

    static long calc(long n) {
        count++;
//        System.out.println("calc: " + n);
        if (map.containsKey(n)) {
            return map.get(n);
        }

        long half = n / 2;

        long p = calc(half);
        long p_1 = calc(half - 1);
        long result = 0L;
        if (n % 2 == 0) {
            result = (p * p % divider + 2 * p * p_1 % divider) % divider;
        } else {
            result = (2 * p * p % divider + 2 * p * p_1 % divider + p_1 * p_1 % divider) % divider;
        }
        map.put(n, result);
        return result;
    }

}

// A*B*C*D*E
// A*B+C*D*E
// A*(B+C)*D*E
// (A*B+C)*(D*E)

