import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringBuilder sb = new StringBuilder();
	
	public static int n,m,t;
	public static int[][] arr;
	
	public static boolean succ;
	public static void init() throws IOException{
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		t = Integer.parseInt(stk.nextToken());
		arr = new int[n + 1][m + 1];
		
		for(int i = 1; i <= n; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		for(int i = 1; i <= t; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			turn(a,b,c);
			succ = false;
			rm();
			if(!succ) avg();
		}
	}
	
	public static void turn(int idx, int dir, int k) {
		for(int x = idx; x <= n; x += idx) {
			for(int i = 0; i < k; i++) {
				if(dir == 0) {
					int tmp = arr[x][m];
					for(int j = m; j >= 2; j--) {
						arr[x][j] = arr[x][j - 1]; 
					}
					arr[x][1] = tmp;
				}
				else {
					int tmp = arr[x][1];
					for(int j = 2; j <= m; j++) {
						arr[x][j - 1] = arr[x][j]; 
					}
					arr[x][m] = tmp;
				}
			}
		}
	}
	public static void avg() {
		int sum = 0;
		int cnt = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(arr[i][j] != -1) {
					cnt++;
					sum += arr[i][j];
				}
			}
		}
		double avg = (double)sum / cnt;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(arr[i][j] == -1) continue;
				if(arr[i][j] > avg) arr[i][j]--;
				else if(arr[i][j] < avg) arr[i][j]++;
			}
		}
	}
	
	public static int dx[] = {1,0,-1,0};
	public static int dy[] = {0,1,0,-1};
	
	public static class Node{
		int x,y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static boolean OOB(int x) {
		if(x > n || x < 1) return true;
		return false;
	}
	
	public static void bfs(int x, int y) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x,y));
		boolean visited[][] = new boolean[n + 1][m + 1];
		visited[x][y] = true;
		int val = arr[x][y];
		boolean flag = false;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for(int i = 0; i < 4 ; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(ny < 1) ny += m;
				if(ny > m) ny -= m;
				if(OOB(nx)) continue;
				if(arr[nx][ny] != val) continue;
				q.add(new Node(nx,ny));
				arr[nx][ny] = -1;
				flag = true;
				visited[nx][ny] = true;
			}
		}
		if(flag) {
			arr[x][y] = -1;
			succ = true;
		}
	}
	
	public static void rm() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(arr[i][j] != -1) {
					bfs(i,j);
				}
			}
		}
	}
	
	public static void solution() throws IOException{
		init();
		int sum = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(arr[i][j] != -1) sum += arr[i][j];
			}
		}
		sb.append(sum);
		bw.append(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void main(String[] args) throws IOException{
		solution();
	}
	
}
