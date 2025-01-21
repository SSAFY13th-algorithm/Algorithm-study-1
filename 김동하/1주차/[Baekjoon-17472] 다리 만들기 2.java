package baekjoon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static int n,m;
	public static int arr[][] = new int[11][11];
	public static boolean visited[][] = new boolean[11][11];
	public static int dx[] = {1,0,-1,0};
	public static int dy[] = {0,1,0,-1};
	public static int landSize = 0;
	public static void init() {
		n = sc.nextInt();
		m = sc.nextInt();
		for(int i = 1; i <= 6; i++) {
			parent[i] = i;
		}
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
	}
	public static boolean OOB(int x, int y) {
		if(x > n || x < 1 || y > m || y < 1) return true;
		return false;
	}
	static class Node{
		int x,y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Node() {};
	}
	public static List<List<Node>> lands = new ArrayList<>();
	public static int parent[] = new int[7];
	public static void bfs(int a, int b) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a,b));
		List<Node> land = new ArrayList<>();
		land.add(new Node(a,b));
		visited[a][b] = true;
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
		
			q.remove();
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(OOB(nx,ny) || visited[nx][ny] || arr[nx][ny] == 0) continue;
				visited[nx][ny] = true;
				land.add(new Node(nx,ny));
				q.add(new Node(nx,ny));
			}
		}
		lands.add(land);
	}
	public static void addLand() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					bfs(i,j);
					landSize++;
				}
			}
		}
	}
	static class Route{
		int idx1, idx2, size;
		public Route(int idx1, int idx2, int size) {
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.size = size;
		}
	}
	
	public static int findParent(int x) {
		if(x == parent[x]) return x;
		return findParent(parent[x]);
	}
	
	public static void unionParent(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		if(x > y) parent[x] = y;
		else parent[y] = x;
	}
	
	public static boolean isAllUnion() {
		int tmp = findParent(1);
		for(int i = 2; i <= landSize; i++) {
			if(tmp != findParent(i)) return  false;
		}
		return true;
	}
	
	public static PriorityQueue<Route> route = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.size,o2.size));
	public static void findRoute(int idx1, int idx2) {
		List<Node> land1 = lands.get(idx1);
		List<Node> land2 = lands.get(idx2);
		int cnt = Integer.MAX_VALUE;
		for(int i = 0; i < land1.size(); i++) {
			int x1 = land1.get(i).x;
			int y1 = land1.get(i).y;
			for(int j = 0; j < land2.size(); j++) {
				int x2 = land2.get(j).x;
				int y2 = land2.get(j).y;
				if(x1 != x2 && y1 != y2) continue;
				int ret = 1;
				if(x1 == x2) {
					if(y1 > y2) {
						boolean flag = true;;
						for(int k = y2 + 1; k < y1; k++) {
							if(arr[x1][k] == 1) {
								flag = false;
								break;
							}
						}
						if(flag) ret = y1 - y2 - 1;
					}
					else {
						boolean flag = true;;
						for(int k = y1 + 1; k < y2; k++) {
							if(arr[x1][k] == 1) {
								flag = false;
								break;
							}
						}
						if(flag) ret = y2 - y1 - 1;
					}
				}
				else if(y1 == y2) {
					if (x1 > x2) {
						boolean flag = true;;
						for(int k = x2 + 1; k < x1; k++) {
							if(arr[k][y1] == 1) {
								flag = false;
								break;
							}
						}
						if(flag) ret = x1 - x2 - 1;
					}
					else {
						boolean flag = true;;
						for(int k = x1 + 1; k < x2; k++) {
							if(arr[k][y1] == 1) {
								flag = false;
								break;
							}
						}
						if(flag) ret = x2 - x1 - 1;
					}
				}
				if(ret < 2) continue;
				cnt = Math.min(ret, cnt);
			}
		}
		if(cnt != Integer.MAX_VALUE) route.offer(new Route(idx1 + 1,idx2 + 1,cnt));
	}
	public static void makeRoute() {
		for(int i = 0; i < landSize; i++) {
			for(int j = i + 1; j < landSize; j++) {
				findRoute(i,j);
			}
		}
	}
	
	public static void solution() {
		init();
		addLand();
		makeRoute();
		int ans = 0;
		while(!route.isEmpty()) {
			Route r = route.peek();
			route.remove();
			int s = r.size;
			int idx1 = r.idx1;
			int idx2 = r.idx2;

			if(findParent(idx1) == findParent(idx2)) continue;
			unionParent(idx1, idx2);
			ans += s;
			if(isAllUnion()) {
				System.out.println(ans);
				return;
			}
			
		}
		System.out.println(-1);
	}
	public static int makeArray(int[] arr) {
		int sum = 0;
		for(int i : arr) {
			sum += i;
		}
		return sum;
	}
	public static void main(String[] args) {
		solution();
	}
}
