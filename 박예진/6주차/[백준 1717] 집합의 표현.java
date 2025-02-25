import java.io.*;
import java.util.*;

/*
 * 유니온파인드
 * 합집합 0 a b
 * 두 원소가 같은 집합에 포함되어있는지 확인 1 a b
 * */

public class Main {
	
	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 부모 초기화
		parent = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 합집합
			if (num == 0) {
				unionParent(a, b);
			}
			// 두 원소가 같은 집합에 포함
			else if (num == 1) {
				System.out.println(getParent(a) == getParent(b) ? "YES" : "NO");
			}
		}
	}
	
	static int getParent(int n) {
		if (parent[n] == n) return n;
		else return parent[n] = getParent(parent[n]);
	}
	
	static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a > b) parent[a] = b;
		else parent[b] = a;
	}
	
}
