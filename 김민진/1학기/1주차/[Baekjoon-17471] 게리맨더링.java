import java.util.*;
import java.io.*;

public class Main {

	static int	n;
	static int	ans;

	static int[]	population;
	static int[]	section;	// store 1 or 2

	static boolean[]	visited;

	static ArrayList<Integer>[]	graph;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	static void	init() throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;

		graph = new ArrayList[n+1];
		population = new int[n+1];
		section = new int[n+1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<>();
		}

		// create graph
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) {
				int to = Integer.parseInt(st.nextToken());
				graph[i].add(to);
			}
		}
	}

	static void	solution() {
		dfs(1);
		System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
	}

	static void	dfs(int depth) {
		// all section visited
		if (depth == n + 1) {
			int sec1 = 0;
			int sec2 = 0;

			for (int i = 1; i <= n; i++) {
				if (section[i] == 1) sec1 += population[i];
				else sec2 += population[i];
			}

			checkValid(sec1, sec2);

			return ;
		}

		// mark as section1
		section[depth] = 1;
		dfs(depth + 1);

		// mark as section2
		section[depth] = 2;
		dfs(depth + 1);
	}

	/**
	 * check if the city is divided into 2 parts or not
	 *
	 * @param sec1 	section 1
	 * @param sec2 	section 2
	 */
	static void	checkValid(int sec1, int sec2) {
		int cnt = 0;

		visited = new boolean[n+1];
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				bfs(i, section[i]);
				cnt++;
			}
		}

		// if divided into two parts
		if (cnt == 2) {
			ans = Math.min(ans, Math.abs(sec1 - sec2));
		}
	}

	/**
	 * group the sections marked with label
	 *
	 * @param idx	index of graph to search
	 * @param label	label of section
	 */
	static void bfs(int idx, int label) {
		Queue<Integer>  q = new ArrayDeque<>();

		q.offer(idx);
		visited[idx] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 0; i < graph[cur].size(); i++) {
				int nxt = graph[cur].get(i);

				// same section
				if (!visited[nxt] && section[nxt] == label) {
					q.offer(nxt);
					visited[nxt] = true;
				}
			}
		}
	}
}
