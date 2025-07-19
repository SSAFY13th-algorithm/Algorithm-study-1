import java.io.*;
import java.util.*;

public class Main {

	private static int  n;
	private static int  m;
	private static int  k;
	private static int  x;

	private static int[]    dist;

	private static List<Integer>    ans;

	private static List<List<Integer>>  graph;
	private static ArrayDeque<Integer>  dq;

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		dist = new int[n+1];
		ans = new ArrayList<>();
		dq = new ArrayDeque<>();

		graph = new ArrayList<>();
		for (int i = 0; i < n+1; i++) {
			graph.add(new ArrayList<>());
		}

		Arrays.fill(dist, -1);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
		}
	}

	private static void sol() {
		dq.add(x);
		dist[x] = 0;

		bfs();
		printAns();
	}

	private static void bfs() {
		while (!dq.isEmpty()) {
			int cur = dq.poll();

			if (dist[cur] == k) {
				ans.add(cur);
			} else if (dist[cur] > k) return;

			for (int node : graph.get(cur)) {
				if (dist[node] == -1) {
					dist[node] = dist[cur] + 1;
					dq.add(node);
				}
			}
		}
	}

	private static void printAns() {
		if (ans.isEmpty()) {
			System.out.println(-1);
			return;
		}

		ans.sort(Comparator.naturalOrder());
		for (int an : ans) {
			System.out.println(an);
		}
	}

}