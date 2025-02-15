package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2Delievering {

	private static final int	INF = Integer.MAX_VALUE;

	private static int	N;
	private static int	M;

	private static int[]		dist;
	private static boolean[]	visited;

	private static Queue<Node>	pq;
	private static List<Node>[]	graph;

	static class Node implements Comparable<Node> {
		int	idx;
		int	dist;

		Node(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dist = new int[N+1];
		Arrays.fill(dist, INF);
		visited = new boolean[N+1];
		pq = new PriorityQueue();

		graph = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int	a = Integer.parseInt(st.nextToken());
			int	b = Integer.parseInt(st.nextToken());
			int	c = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
	}

	private static void sol() {
		// start
		dist[1] = 0;
		pq.offer(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node	cur = pq.poll();

			if (visited[cur.idx]) continue;
			visited[cur.idx] = true;

			for (Node nxt : graph[cur.idx]) {
				if (dist[nxt.idx] > dist[cur.idx] + nxt.dist) {
					dist[nxt.idx] = dist[cur.idx] + nxt.dist;
					// nxt.idx까지 도달하기까지의 거리 업데이트
					pq.offer(new Node(nxt.idx, dist[nxt.idx]));
				}
			}
		}
		System.out.println(dist[N]);
	}
}
