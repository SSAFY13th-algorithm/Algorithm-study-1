import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 도시_건설 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nodeCnt = Integer.parseInt(st.nextToken());
		int edgeCnt = Integer.parseInt(st.nextToken());
		parents = new int[nodeCnt+1];
		for (int i = 0 ; i <= nodeCnt; i++) parents[i] = i;
		
		long totalCost = 0;
		long curCostSum = 0;
		int connectCnt = 0;
		
		for (int i = 0; i < edgeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long cost = Integer.parseInt(st.nextToken());
			totalCost += cost;
			pq.add(new Edge(from,to,cost));
		}
		
		while(connectCnt != nodeCnt-1 && !pq.isEmpty()) {
			Edge e = pq.poll();
			if (find(e.from) == find(e.to)) continue;
			
			union(e.from, e.to);
			connectCnt++;
			curCostSum += e.cost;
		}
		
		System.out.println((connectCnt == nodeCnt-1)? totalCost-curCostSum:-1); 
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		if (aRoot < bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
		
		return true;
		
		
	}
	
	private static int find(int a) {
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	static class Edge implements Comparable<Edge>{
		int from,to;
		long cost;

		public Edge(int from, int to, long cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(cost, o.cost);
		}
	}
	
}
