import java.io.*;
import java.util.*;

/*
 * 어떤 씨앗이 꽃이 핀 뒤 다른 꽃잎과 닿게 되면 두 꽃 모두 죽음
 * 꽃잎 나가도 죽음
 * 
 * 서로 다른 세 씨앗을 모두 꽃 피게 하면서, 가장 싼 가격에 화단 대여
 * */

public class Main {
	
	static int N;
	static int answer = Integer.MAX_VALUE;
	static int[][] arr;
	static List<int[]> list;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
					
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 해결
		list = new ArrayList<>();
		dfs(0);
		
		// 결과
		System.out.println(answer);
	}
	
	static void dfs(int depth) {
		// 씨앗 3개 들어왔을 때
		if (depth == 3) {
			int sum = check();
			if (sum != -1) {
				answer = Math.min(answer, sum);
			}
			return;
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				list.add(new int[] {i, j});
				dfs(depth + 1);
				list.remove(list.size() - 1);
			}
		}
	}
	
	static int check() {
		int sum = 0;
		boolean[][] visited = new boolean[N][N];
		for(int[] cur : list) {
			int x = cur[0];
			int y = cur[1];
			visited[x][y] = true;
			sum += arr[x][y];
			
			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) return -1;
				if (visited[nx][ny]) return -1;
				else {
					visited[nx][ny] = true;
					sum += arr[nx][ny];
				}
			}
		}
		return sum;
	}

}
