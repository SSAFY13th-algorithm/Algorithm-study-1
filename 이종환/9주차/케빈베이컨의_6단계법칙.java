import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 케빈베이컨의_6단계법칙 {
	static int nodeCnt;
	static int edgeCnt;
	static int[][] nodes;
	static int min = Integer.MAX_VALUE;
	static int minIdx = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		
		nodes = new int[nodeCnt+1][nodeCnt+1];
		for (int i = 0; i <= nodeCnt; i++) {
			Arrays.fill(nodes[i], Integer.MAX_VALUE/2);
			nodes[i][i] = 0;
		}
		
		for (int i = 0; i < edgeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			nodes[node1][node2] = 1;
			nodes[node2][node1] = 1;
		}
		
		// Floyd Warshall Algorithm
		floydWarshall();
		getMin();
		
		System.out.println(minIdx);
	}

	private static void getMin() {
		for (int i = 1; i <=nodeCnt;i++) {
			int temp = 0;
			for (int j = 1; j<=nodeCnt; j++) temp+= nodes[i][j];
			System.out.printf("%d에서  합:%d\n",i,temp);
			
			if (min > temp) {	
				min = temp;
				minIdx = i;
			}
		}

		
	}

	private static void floydWarshall() {
		for (int k = 1; k<= nodeCnt; k++) {
			for (int i = 1; i <= nodeCnt; i++) {
				for (int j = 1; j<= nodeCnt; j++) {
					nodes[i][j] = Math.min(nodes[i][j], nodes[i][k]+nodes[k][j]);
				}
			}
		}
		
	}
}
