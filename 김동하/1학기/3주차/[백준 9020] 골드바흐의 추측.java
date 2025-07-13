package one;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringBuilder sb = new StringBuilder();
	public static int n;

	public static void init() throws IOException{
		n = Integer.parseInt(br.readLine());
		int k;
		for(int i = 0; i < n; i++) {
			k = Integer.parseInt(br.readLine());
			findPrime(k);
		}
		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static boolean isPrime(int x) {
		int tmp = (int)Math.sqrt(x);
		if(x == 1) return false;
		if (x % 2 == 1) {
			for (int j = 2; j <= tmp; j ++ ) {
				if (x % j == 0) return false;
				if (j == tmp)return true;
			}
		}
		
		return true;
	}
	
	public static void findPrime(int k) {
		for(int i = k / 2; i >= 2; i--) {
			if(i != 2 && i % 2 == 0) continue;
			if(isPrime(i) && isPrime(k - i)) {
				sb.append(i).append(" ").append(k - i).append("\n");
				return;
			}
		}		
	}
	
	public static void main(String[] args)throws IOException {
		init();
	}

}
