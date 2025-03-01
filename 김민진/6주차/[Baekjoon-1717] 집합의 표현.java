package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4RepresentingSet {
	
	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder   sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	
	private static int[] parent;
	
	public static void main(String[] args) throws IOException {
		init();
		sol();
		
		System.out.println(sb.toString());
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			parent[i] = i;
		}
	}
	
	private static void sol() throws IOException {
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (cmd == 0) {
				union(a, b);
			} else if (cmd == 1) {
				sb.append(checkUnion(a, b) ? "YES\n" : "NO\n");
			}
		}
	}
	
	private static void union(int x, int y) {
		int a = find(x);
		int b = find(y);
		
		if (a == b) return ;
		
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
	
	private static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		
		return parent[x];
	}
	
	private static boolean checkUnion(int a, int b) {
		return find(a) == find(b);
	}
	
}
