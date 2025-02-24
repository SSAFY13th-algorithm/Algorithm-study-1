import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//dp로 접근
		StringTokenizer st = new StringTokenizer(br.readLine());
		int rowCnt = Integer.parseInt(st.nextToken());
		int colCnt = Integer.parseInt(st.nextToken());

		// 한층씩 내려가면서 dp를 진행
		// 이때, dp배열은 3차원으로 만든다. 3차원에는 마지막 이동방향을 저장한다.
		// 0은 왼쪽 아래로, 1은 아래로, 2는 오른쪽 아래로 움직였음을 의미
		int[][] arr = new int[rowCnt][colCnt];
		
		for (int i = 0; i < rowCnt; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < colCnt; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[rowCnt][colCnt][3];
		
		for(int i = 0; i <colCnt ; i++) {
			if (i == 0 ) {
				dp[0][i][2] = 1000000000;
			}else {
				dp[0][i][2] = arr[0][i];
			}
			
			if (i == colCnt-1) {
				dp[0][i][0] = 1000000000;
			} else {
				dp[0][i][0] = arr[0][i];
			}
			
			dp[0][i][1] = arr[0][i];
			
		}
		
		for (int i = 1; i < rowCnt; i++) {
			for (int j = 0; j < colCnt; j++) {
				// 이 지점에 어떻게 도착하는 지에 대한 최댓값 각각 구함
				
				
				int downStraight = Math.min(dp[i-1][j][0], dp[i-1][j][2]);
				dp[i][j][1] = downStraight + arr[i][j];
				
				if ( j < colCnt-1) { // 오른쪽 끝이면 왼쪽 아래로 내려와서 도착이 불가능하므로
					int downLeft = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]);
					dp[i][j][0] = downLeft + arr[i][j];
				} else {
					dp[i][j][0] = 1000000000;
				}
				
				if ( j != 0) { // 같은 이치
					int downRight = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]);
					dp[i][j][2] = downRight + arr[i][j];
				} else {
					dp[i][j][2] = 1000000000;
				}			
			}
		}
		
		
		
		int min = 1000000000;
		for (int i = 0; i < colCnt; i++) {
			for (int j = 0; j < 3; j++) {
				min = Math.min(min, dp[rowCnt-1][i][j]);
			}
		}
		System.out.println(min);
		

	}


}

