import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소셜네트워킹_어플리케이션 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int tc, TC;
	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC =Integer.parseInt(br.readLine());
		for (tc = 1; tc <= TC; tc++) {
			init();
			write();
		}
		print();
	}


	private static void init() throws NumberFormatException, IOException {
		int nodeCnt = Integer.parseInt(br.readLine());
		int edgeCnt = Integer.parseInt(br.readLine());
		
		parents = new int[nodeCnt];
		for (int i = 0; i < nodeCnt; i++) parents[i] = i;
		
		for (int i = 0; i < edgeCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from, to);
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		if (aRoot < bRoot) {
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}
		
		return true;
	}

	private static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

	private static void write() throws NumberFormatException, IOException {
		
		sb.append("Scenario ").append(tc).append(":\n");
		
		int tryCnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < tryCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(find(a) == find(b)?1:0).append("\n");
		}
		if (tc != TC)sb.append("\n");
	}
	

	private static void print() {System.out.println(sb.toString());}
}
