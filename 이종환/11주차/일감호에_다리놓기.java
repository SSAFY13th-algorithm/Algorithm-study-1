import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 일감호에_다리놓기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int roomCnt,banCnt;
	static long rockCnt,cost;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}
	private static void print() {
		// 무조건 다 연결될 수 있기에 아예 연결자체가 안되는 경우는 배제
		System.out.println(cost <= rockCnt?"YES":"NO");
	}
	private static void process() {
		// 아예 섬이랑 연결이 되지 않아도 되는 경우를 위한 변수 생성
		boolean hasIsland = false; 
		int connectCnt =1 ;
		while(!pq.isEmpty()) {
			
			Edge e = pq.poll();
//			System.out.printf("%d와 %d가 가중치 %d로 연결\n",e.from,e.to,e.weight);
			if (union(e.from, e.to)) {
				cost+= e.weight;
				connectCnt++;
				if (e.to == 0) hasIsland = true;
			}
			
			if (!hasIsland && connectCnt == roomCnt) return;
		}
		
	}
	
	private static int find(int a) {
		if ( a== parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		if (aRoot>bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
		
		return true;
	}
	
	
	
	private static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		cost = 0l;
		roomCnt = Integer.parseInt(st.nextToken());
		banCnt = Integer.parseInt(st.nextToken());
		rockCnt = Long.parseLong(st.nextToken());
		parents = new int[roomCnt+1]; // 0번은 섬
		for (int i = 1; i <= roomCnt; i++) parents[i] = i;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= roomCnt; i++) pq.add(new Edge(i,0,Integer.parseInt(st.nextToken())));
		
		boolean[] isBlocked = new boolean[roomCnt+1]; // i 번째가 i+1번쨰와 연결되어있는 지 여부
		for (int i = 0; i < banCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int t1 = Integer.parseInt(st.nextToken());
			int t2 = Integer.parseInt(st.nextToken());
			if (Math.abs(t1-t2) == 1) isBlocked[Math.min(t1, t2)] = true;
			else isBlocked[Math.max(t1, t2)] = true;
		}
		
		for (int i = 1; i <= roomCnt; i++) {
			if (isBlocked[i]) continue;
			if (i == roomCnt) pq.add(new Edge(i,1,0));
			else pq.add(new Edge(i,i+1,0));
		}
		

	}
	
	
	static class Edge implements Comparable<Edge>{
		int from,to,weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
