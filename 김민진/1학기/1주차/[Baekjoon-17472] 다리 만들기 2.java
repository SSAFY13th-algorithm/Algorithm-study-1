package main;

import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	
	static int	n;
	static int	m;
	static int	label;
	
	static int[]	parent;
	static int[]	dx = { 0, 1, 0, -1 };
	static int[]	dy = { 1, 0, -1, 0 };

	static int[][]	map;
	
	static PriorityQueue<MSTNode>	pq;
	
	/**
	 * elements of Priority Queue
	 */
	static class	MSTNode implements Comparable<MSTNode> {
		int	from;
		int	to;
		int	len;
		
		public	MSTNode(int from, int to, int len) {
			this.from = from;
			this.to = to;
			this.len = len;
		}
		
		// To order Priority Queue
		@Override
		public int	compareTo(MSTNode mn) {
			return this.len - mn.len;
		}
	}
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		pq = new PriorityQueue<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. numbering islands
		labelIslands();
		
		// 2. find the shortest bridge
		findShortestBridge();
		
		// 3. MST
		findMinimumSpanningBridges();
	}
	
	/**
	 * numbering islands
	 */
	static void	labelIslands() {
		boolean[][]	visited = new boolean[n][m];
		
		label = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				// if it is sea or already labeled || visited
				if (map[i][j] != 1 || visited[i][j]) continue ;
				
				Queue<Point>	q = new ArrayDeque<>();
				
				q.offer(new Point(i, j));
				map[i][j] = ++label;
				visited[i][j] = true;
				
				while (!q.isEmpty()) {
					Point	cur = q.poll();
					int		cx = cur.x;
					int		cy = cur.y;

					for (int d = 0; d < 4; d++) {
						int	nx = cx + dx[d];
						int	ny = cy + dy[d];
					
						if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny]) {
							if (map[nx][ny] == 1) {
								map[nx][ny] = label;
								visited[nx][ny] = true;
								q.offer(new Point(nx, ny));
							}
						}
					}
				}
			}
		}
		
		parent = new int[label + 1];
		// setting parent nodes
		for (int i = 0; i < label+1; i++) {
			parent[i] = i;
		}
	}
	
	/**
	 * find the shortest bridge between two islands and put them into Priority Queue
	 */
	static void	findShortestBridge() {
		boolean[][]	visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				// if it is sea or already visited
				if (map[i][j] == 0 || visited[i][j]) continue ;
				
				Queue<int[]>	q = new ArrayDeque<>();
				
				q.offer( new int[] { i, j, 0 } );
				visited[i][j] = true;
				
				while (!q.isEmpty()) {
					int[]	cur = q.poll();
					int		cx = cur[0];
					int		cy = cur[1];
					
					for (int d = 0; d < 4; d++) {
						int	nx = cx + dx[d];
						int	ny = cy + dy[d];
						
						if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny]) {
							checkBridge(nx, ny, d, map[cx][cy], 0);
						}
					}
				}
			}
		}
	}

	/**
	 * check if a bridge can be built
	 * 
	 * @param	nx, ny	next x, y
	 * @param	dir		direction
	 * @param	from	the island where the bridge starts
	 * @param	len		the length of bridge
	 */
	static void	checkBridge(int nx, int ny, int dir, int from, int len) {
		// out of bound
		if (nx < 0 || nx >= n || ny < 0 || ny >= m) return ;

		// if it is sea
		if (map[nx][ny] == 0) {
			checkBridge(nx + dx[dir], ny + dy[dir], dir, from, len + 1);
		} else {
			// if the island is different && the length of bridge is at least 2
			if (map[nx][ny] != from && len > 1) {
				pq.offer(new MSTNode(from, map[nx][ny], len));
			}
		}
	}
	
	/**
	 * find the minimum length of bridges to connect all islands
	 */
	static void	findMinimumSpanningBridges() {
		int			sum = 0;
		int			cnt = 0;
		boolean[]	linked = new boolean[label + 1];

		while (!pq.isEmpty()) {
			MSTNode	mn = pq.poll();

			int	p1 = findParent(mn.from);
			int	p2 = findParent(mn.to);

			// if the parent nodes are different
			if (p1 != p2) {
				union(p1, p2);
				sum += mn.len;
				linked[mn.from] = true;
				linked[mn.to] = true;
				cnt++;
			}
		}

		// if islands are not connected
		if (sum == 0) {
			System.out.println(-1);
		} else {
			// if all islands are connected
			if (cnt == label - 1) {
				System.out.println(sum);
			} else {
				System.out.println(-1);
			}
		}

	}
	
	/**
	 * find the parent node
	 * 
	 * @param	n	node
	 * @return		parent node
	 */
	static int	findParent(int n) {
		if (parent[n] == n) return n;
		return parent[n] = findParent(parent[n]);
	}
	
	/**
	 * union two nodes
	 * 
	 * @param	n1, n2	nodes
	 */
	static void	union(int n1, int n2) {
		int	p1 = findParent(n1);
		int p2 = findParent(n2);
		
		if (p1 < p2) {
			parent[p2] = p1;
		} else {
			parent[p1] = p2;
		}
	}
}

