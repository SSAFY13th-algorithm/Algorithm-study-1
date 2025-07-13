import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb;
	public static int ans;
	public static int n,m;
	public static List<List<Node>> edges = new ArrayList<>();
	
	public static void init() throws IOException {
		int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = in[0];
		m = in[1];
		dist = new int[n + 1];
		for(int i = 0; i <= n; i++) {
			edges.add(new ArrayList<>());
		}
		for(int i = 1; i <= n; i++) {
			dist[i] = 987654321;
		}
		for(int i = 0; i < m; i++) {
			in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			edges.get(in[0]).add(new Node(in[1],in[2]));
			edges.get(in[1]).add(new Node(in[0],in[2]));
		}
	}
	public static int dist[];
	
	public static class Node implements Comparable<Node>{
		int idx, cnt;
		public Node(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt;
		}
	}
	
	public static void dijkstra() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(1,0));
		dist[1] = 0;
		boolean visited[] = new boolean[n + 1];
		while(!q.isEmpty()) {
			Node cur = q.poll();
			if(visited[cur.idx]) continue;
			visited[cur.idx] = true;
			for(Node next : edges.get(cur.idx)) {
				if(dist[next.idx] > dist[cur.idx] + next.cnt) {
					dist[next.idx] = dist[cur.idx] + next.cnt;
					q.add(new Node(next.idx,dist[next.idx]));
				}
			}
		}
	}
	
	public static void solution() throws IOException{
		init();
		dijkstra();
		System.out.println(dist[n]);
	}
	public static void main(String[] args) throws IOException {
		solution();
	}
}
