import java.io.*;
import java.util.*;

/*
 * 같은 방향으로 두번 연속으로 움직일 수 없음
 * 아래 왼쪽 1, 아래 2, 아래 오른쪽 3
 * */

public class Main {
    
	static int N, M;
	static int[][] arr;
	static int[][][] dp; // 0 위 왼쪽, 1 위, 2 위 오른쪽
	static final int INF = 1000000000;
	static int answer = INF;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N + 1][M + 2];
        dp = new int[N + 1][M + 2][3];
        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 1; j <= M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 해결
        dynamic();
        
        // 출력
        for(int i = 1; i <= M; i++) {
        	for(int k = 0; k < 3; k++) {
            	answer = Math.min(answer, dp[N][i][k]);
        	}
        }
        System.out.println(answer);
    }
    
    static void dynamic() {
    	// 초기화
    	for(int j = 1; j <= M; j++) {
    		for(int k = 0; k < 3; k++) {
        		dp[1][j][k] = arr[1][j]; // 0행 값
    		}
    	}
    	// 왼쪽, 오른쪽 열 INF
    	for(int i = 0; i <= N; i++) {
        	for(int k = 0; k < 3; k++) {
        		dp[i][0][k] = INF;
        		dp[i][M + 1][k] = INF;
        	}
    	}

    	// 해결
    	for(int i = 2; i <= N; i++) {
    		for(int j = 1; j <= M; j++) {
    			// 세가지 방향 전부 계산
    			for(int k = 0; k < 3; k++) {
    				int minNum = INF;
    				
    				if (k == 0) {
    					int n1 = dp[i - 1][j - 1][1];
    					int n2 = dp[i - 1][j - 1][2];
    					minNum = Math.min(n1, n2);
    				}
    				else if (k == 1) {
    					int n0 = dp[i - 1][j][0];
    					int n2 = dp[i - 1][j][2];
    					minNum = Math.min(n0, n2);
    				}
    				else if (k == 2){
    					int n0 = dp[i - 1][j + 1][0];
    					int n1 = dp[i - 1][j + 1][1];
    					minNum = Math.min(n0, n1);
    				}
					  dp[i][j][k] = minNum + arr[i][j];
    			}   			
    		}
    	}
    }

}
