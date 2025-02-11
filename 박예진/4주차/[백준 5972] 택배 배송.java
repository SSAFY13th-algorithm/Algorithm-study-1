import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	static List<Edge> graph[];
	
	static class Edge implements Comparable<Edge> {
		private int n, cost;
		
		public Edge(int n, int cost) {
			this.n = n;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge edge) {
			return Integer.compare(this.cost, edge.cost);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
        	graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	
        	graph[u].add(new Edge(v, cost));
        	graph[v].add(new Edge(u, cost));
        }
        
        // 해결
        dijkstra();
        
        // 출력
        System.out.println(dist[N]);
    }
    
    public static void dijkstra() {
    	dist = new int[N + 1];
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	
    	Arrays.fill(dist, INF);
    	dist[1] = 0;
    	pq.add(new Edge(1, 0));
    	
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll();
    		int cost = cur.cost;
    		int now = cur.n;
    		
    		// 이미 처리된 노드
    		if(dist[now] < cost) continue;
    		
    		for(Edge neighbor : graph[now]) {
    			int nextcost = neighbor.cost;
    			int next = neighbor.n; 
    			
    			if (dist[next] > dist[now] + nextcost) {
    				dist[next] = dist[now] + nextcost;
    				pq.add(new Edge(next, dist[next]));
    			}
    		}
    	}
    }

}
