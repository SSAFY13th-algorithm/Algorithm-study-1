import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,q;
    
    public static int arr[][];
    public static int level[];
    
    public static int sum;
    public static int ans;
    
    public static class Node{
    	int x,y;
    	public Node(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    
    public static List<Node> meltList;
    
    public static int dx[] = {1,0,-1,0};
    public static int dy[] = {0,1,0,-1};
    
    public static boolean[][] visited;
    
    public static void init() throws IOException {
    	StringTokenizer stk = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(stk.nextToken());
    	q = Integer.parseInt(stk.nextToken());
    	
    	n = (1<<n);
    	arr = new int[n + 1][n + 1];
    	for(int i = 1; i <= n; i++) {
    		stk = new StringTokenizer(br.readLine());
    		for(int j = 1; j <= n; j++) {
    			arr[i][j] = Integer.parseInt(stk.nextToken());
    		}
    	}
    	level = new int[q + 1];
    	stk = new StringTokenizer(br.readLine());
    	for(int i = 1; i <= q; i++) {
    		level[i] = Integer.parseInt(stk.nextToken());
    	}
    }
    public static void turnArr(int x, int y, int size) {
    	int tmp[][] = new int[size + 1][size + 1];
    	int copied[][] = new int[size + 1][size + 1];
    	for(int i = x; i < x + size; i++) {
    		for(int j = y; j < y + size; j++) {
    			copied[i - x + 1][j - y + 1] = arr[i][j];
    		}
    	}
    	for(int i = 1; i <= size; i++) {
    		for(int j = 1; j <= size; j++) {
    			tmp[j][size - i + 1] = copied[i][j];
    		}
    	}
    	for(int i = x; i < x + size; i++) {
    		for(int j = y; j < y + size; j++) {
    			arr[i][j] = tmp[i - x + 1][j - y + 1];
    		}
    	}
    }
    
    public static boolean OOB(int x, int y) {
    	if(x > n || x < 1 || y > n || y < 1 || arr[x][y] <= 0) return true;
    	return false;
    }
    
    public static int adj(int x, int y) {
    	int cnt = 0;
    	for(int i = 0; i < 4; i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		if(OOB(nx,ny)) continue;
    		cnt++;
    	}
    	return cnt;
    }
    
    public static void melting() {
    	meltList = new ArrayList<>();
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= n; j++) {
    			if(arr[i][j] < 1) continue;
    			int cnt = adj(i,j);
    			if(cnt < 3) meltList.add(new Node(i,j));
    		}
    	}
    	for(Node n : meltList) {
    		arr[n.x][n.y]--;
    	}
    }
    
    public static int bfs(int x, int y) {
    	Queue<Node> q = new ArrayDeque<>();
    	q.add(new Node(x,y));
    	visited[x][y] = true;
    	sum += arr[x][y];
    	int cnt = 1;
    	while(!q.isEmpty()) {
    		Node cur = q.poll();
    		for(int i = 0; i < 4; i++) {
    			int nx = cur.x + dx[i];
    			int ny = cur.y + dy[i];
    			if(OOB(nx,ny) || visited[nx][ny]) continue;
    			cnt++;
    			sum += arr[nx][ny];
    			visited[nx][ny] = true;
    			q.add(new Node(nx,ny));
    		}
    	}
    	return cnt;
    }
    
    public static void solution() throws IOException {
    	init();
    	for(int k = 1; k <= q; k++) {
    		int size = (1 << level[k]);
    		for(int i = 1; i <= n; i+= size) {
    			for(int j = 1; j <= n; j+=size) {
    				turnArr(i,j,size);
    			}
    		}
    		melting();
    	}
    	visited = new boolean[n + 1][n + 1];
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= n; j++) {
    			if(!visited[i][j] && arr[i][j] > 0) {
    				ans = Math.max(ans, bfs(i,j));
    			}
    		}
    	}
    	sb.append(sum).append("\n").append(ans);
    	bw.append(sb.toString());
    	bw.flush();
    	bw.close();
    	br.close();
    }

    public static void main(String[] args) throws IOException {
    	solution();
    }
}