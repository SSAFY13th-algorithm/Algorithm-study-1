

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이조각 {
	static int height,width;
	static int max = 0;
	static int mask = 0;
	static int[] dy = {0,1};
	static int[] dx = {1,0};
	static int[][] arr;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		arr = new int[height][width];
		for (int i = 0; i < height; i++) {
			String input = br.readLine();
			for (int j = 0; j < width; j++) arr[i][j] = input.charAt(j) - '0';
		}
		int total = 1 << height*width;
		
		
		for (int i = 0; i <= total; i++) {
			mask = i;
			calculate();
		}
		
		System.out.println(max);
	}
	
	private static void calculate() {
		visited = new boolean[height][width];
		// arr[i][j]가 세로면 width*i +j 가 1, 아니면 0
		// 핵심은 결국 그 칸이 가로로 연결되는 곳인지, 세로로 연결되는 곳인지 고려하는 것.
		int tempSum = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(visited[i][j]) continue;
				visited[i][j] = true;
				
				int dir =(mask & (1<< (i*width + j))) != 0 ?1:0;
				tempSum += getNum(i,j,dir);	
			}
		}
		max = Math.max(tempSum, max);
	}
	
	private static int getNum(int y, int x, int dir) {
		int temp = arr[y][x];
		
		while(true) {
			y += dy[dir];
			x += dx[dir];
			
			if(x <0 || y <0 || y >= height || x >= width || visited[y][x]) break;
			int curDir = (mask & (1<< (y*width + x))) != 0 ?1:0;
			if (curDir != dir) break;
			
			visited[y][x] = true;
			temp = temp*10 + arr[y][x];
		}
	
		return temp;
	}
}