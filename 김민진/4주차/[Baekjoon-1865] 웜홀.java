package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q3Wormhole_array {
	
	private static final int	INF = 50000000;

	private static int	tc;
	private static int	N;
	private static int	M;
	private static int	W;
	
	private static int[]		time;
	private static List<Data>	graph;
	
	private static BufferedReader	br;
	private static StringTokenizer	st;
	
	static class Data {
		int	start;
		int	end;
		int	time;
		
		Data(int start, int end, int time) {
			this.start = start;
			this.end = end;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		sol();		
	}
	
	private static void sol() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < tc; i++) {
			init();
			
			if (bellmanFord()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
	
	private static void init() throws IOException {
		// vertexes, edges, wormholes
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		// init Data Structure
		graph = new ArrayList<>();
		time = new int[N+1];
		
		// road
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int	S = Integer.parseInt(st.nextToken());
			int	E = Integer.parseInt(st.nextToken());
			int	T = Integer.parseInt(st.nextToken());
			
			graph.add(new Data(S, E, T));
			graph.add(new Data(E, S, T));
		}
		
		// wormholes
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			
			int	S = Integer.parseInt(st.nextToken());
			int	E = Integer.parseInt(st.nextToken());
			int	T = Integer.parseInt(st.nextToken()) * -1;
			
			graph.add(new Data(S, E, T));
		}
	}
	
	static boolean bellmanFord() {
		boolean	updated = false;
		// check negative cycle with updated flag
		for (int u = 1; u < N+1; u++) {
			updated = false;
			for (Data d : graph) {
				if (time[d.start] != INF && time[d.end] > time[d.start] + d.time) {
					time[d.end] = time[d.start] + d.time;
					updated = true;
				}
			}
			if (!updated) break;
		}
		return updated;
	}
}
