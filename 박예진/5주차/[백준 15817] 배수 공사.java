import java.util.*;
import java.io.*;

class Main
{
	static int N, x; // 종류의 수, 합친 파이프의 길이
	static int[] L, C; // 파이프의 길이, 수량
	static int[] dp;
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 입력
    	N = Integer.parseInt(st.nextToken());
    	x = Integer.parseInt(st.nextToken());
    	
    	L = new int[N + 1];
    	C = new int[N + 1];
    	
    	for(int i = 1; i <= N; i++) {
    		st = new StringTokenizer(br.readLine());
    		L[i] = Integer.parseInt(st.nextToken());
    		C[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 해결
    	dp = new int[x + 1];
    	dp[0] = 1;
    	dynamic();
    	
    	// 출력
    	System.out.println(dp[x]);
    }
    
    static void dynamic() {
    	// 종류의 수
    	for(int i = 1; i <= N; i++) {
    		int len = L[i];
    		int cnt = C[i];
    		// 만들고 싶은 파이프 길이
    		for(int j = x; j >= 0; j--) {
    			if (dp[j] > 0) {
    				for(int k = 1; k <= cnt; k++) {
    					if (j + len * k <= x) {
    						dp[j + len * k] += dp[j];
    					}
    				}
    			}
    		}
    	}
    }
}
