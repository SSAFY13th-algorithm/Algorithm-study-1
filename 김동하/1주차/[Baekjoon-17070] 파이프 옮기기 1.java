package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static int n;
	public static int[][] arr = new int[17][17];
	public static void init() {
		n = sc.nextInt();
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
	}
	public static boolean OOB(int x, int y) {
		if(x > n || x < 1 || y > n || y < 1 || arr[x][y] == 1) return true;
		return false;
	}
	static class Node{
		int x,y,dir;
		public Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	// 0 : 가로 , 1 : 세로, 2 : 대각선
	public static int[] dx = {0,1,1};
	public static int[] dy = {1,0,1};
	public static int ans = 0;
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(1,2,0));
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			int d = q.peek().dir;
			q.remove();
			if(x == n && y == n) {
				ans++;
			}
			for(int i = 0; i < 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(i == 2) {
					if(arr[nx][ny - 1] == 1 || arr[nx - 1][ny] == 1) continue;
				}
				if(OOB(nx,ny)) continue;
				if(d == 0 && i == 1) continue;
				if(d == 1 && i == 0) continue;
				q.add(new Node(nx,ny,i));
			}
		}
	}

	public static void solution() {
		init();
		bfs();
		System.out.println(ans);
	}
	public static void main(String[] args) {
		solution();
	}
}
