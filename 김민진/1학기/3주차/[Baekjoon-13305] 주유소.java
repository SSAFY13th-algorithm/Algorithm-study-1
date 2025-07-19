package week3;

import java.io.*;
import java.util.*;

public class Q5GasStation {

	private static int	N;

	private static long[]	road;
	private static long[]	gas;

	public static void main(String[] args) throws IOException {

		init();
		sol();

	}

	private static void init() throws IOException {

		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st;

		N = Integer.parseInt(br.readLine());

		road = new long[N-1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N-1; i++) {
			road[i] = Integer.parseInt(st.nextToken());
		}

		gas = new long[N-1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N-1; i++) {
			gas[i] = Integer.parseInt(st.nextToken());
		}
		st.nextElement();	// 버림
	}

	private static void sol() {

		long	cur;
		long 	lowest = gas[0];
		long	total = gas[0] * road[0];

		for (int i = 1; i < N-1; i++) {
			cur = gas[i];

			if (cur < lowest) lowest = cur;

			total += lowest * road[i];
		}

		System.out.println(total);
	}

}
