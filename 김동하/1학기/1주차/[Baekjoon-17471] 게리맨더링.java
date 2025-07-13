package baekjoon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static int n;
	public static int area[] = new int[11];
	public static List<List<Integer>> adj = new ArrayList<>();
	public static boolean[] isContain = new boolean[11];
	public static int ans = Integer.MAX_VALUE;
	public static int sum = 0;
	public static void init() {
		n = sc.nextInt();
		for(int i = 1; i <= n; i++) {
			area[i] = sc.nextInt();
			sum += area[i];
		}
		for(int i = 1; i <= n; i++) {
			int k = sc.nextInt();
			List<Integer> newList = new ArrayList<>();
			for(int j = 0; j < k; j++) {
				int x = sc.nextInt();
				newList.add(x);
			}
			adj.add(newList);
		}
	}
	
	public static boolean bfs(List<Integer> list) {
		if(list.isEmpty()) return false;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(list.get(0));
		int cnt = 1;
		boolean[] visited = new boolean[11];
		visited[list.get(0)] = true;
		while(!q.isEmpty()) {
			int x = q.peek();
			q.remove();
			for(int i = 0; i < adj.get(x - 1).size(); i++) {
				int y = adj.get(x - 1).get(i);
				if(list.contains(y) && !visited[y]) {
					q.add(y);
					visited[y] = true;
					cnt++;
				}
			}
		}
		if(cnt == list.size()) {
			return true;
		}
		return false;
	}
	
	public static void devide(int idx) {
		if(idx == n) { 
			List<Integer> list1 = new ArrayList<>();
			List<Integer> list2 = new ArrayList<>();
			for(int i = 1; i <= n; i++) {
				if(isContain[i]) list1.add(i);
				else list2.add(i);
			}
			
			if(bfs(list1) && bfs(list2)) {
				int sum1 = 0;
				int sum2 = 0;
				for(int i : list1) {
					sum1 += area[i];
				}
				sum2 = sum - sum1;
				ans = Math.min(ans, Math.abs(sum1 - sum2));	
			}
			return;
		}
		isContain[idx + 1] = true;
		devide(idx + 1);
		isContain[idx + 1] = false;
		devide(idx + 1);
	}
	
	public static void solution() {
		init();
		isContain[1] = true;
		devide(1);
		isContain[1] = false;
		devide(1);
		if(ans == Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(ans);
	}
	public static void main(String[] args) {
		solution();
	}
}
