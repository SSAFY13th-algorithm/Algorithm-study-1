package one;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringBuilder sb = new StringBuilder();
	public static int n;
	public static int[] arr = new int[101];
	public static void init() throws IOException{
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
	}
	
	public static int gcd(int x, int y) {
		while(y != 0) {
			int tmp = x % y;
			x = y;
			y = tmp;
		}
		return x;
	}
	
	public static void solution() throws IOException{
		init();
		int finalGcd = Math.abs(arr[0] - arr[1]);
		for(int i = 2; i < n; i++) {
			finalGcd = gcd(finalGcd,Math.abs(arr[i] - arr[i - 1]));
		}
		for(int i = 2; i <= finalGcd; i++) {
			if(finalGcd % i == 0) sb.append(i).append(" ");
		}
		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args)throws IOException {
		solution();
	}

}
