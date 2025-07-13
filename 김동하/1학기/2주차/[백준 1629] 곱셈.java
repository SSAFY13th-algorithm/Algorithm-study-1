package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static long a,b,c;
    public static long[] in;

    public static void init() throws IOException {
        in = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        a = in[0];
        b = in[1];
        c = in[2];
    }

    public static long solution(long x, long y) {
        if(y == 0) return 1;
        if(y % 2 == 1) return ((solution(x, y - 1) * x) % c);
        long tmp = solution(x,y /2) % c;
        return (tmp * tmp) % c;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution(a,b));
    }
}
