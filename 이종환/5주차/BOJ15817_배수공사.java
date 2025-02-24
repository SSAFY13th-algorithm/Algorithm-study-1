import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int pipeTypeCnt = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		
		// 0에는 1
		int[][] pipes = new int[pipeTypeCnt][2];
		
		// dp 
		
		int[] dp = new int[goal+1];
		dp[0] = 1;
		
		for (int i = 0; i < pipeTypeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			pipes[i][0] = Integer.parseInt(st.nextToken()); // 파이프 길이
			pipes[i][1] = Integer.parseInt(st.nextToken()); // 파이프 개수
			
		}
		
		
		//접근법 생각하는데 굉장히 오래걸림
		//주요 포인트: 목표치를 맞추는 것이 아니라
		//1개의 파이프 종류만 있을 때 만들 수 있는 길이와 그 방법의 개수
		//여기에 하나의 종류를 더 추가했을 때 만들 수 있는 길이와 그 방법의 개수...
		//이런식으로 계속 더하면, 자연스럽게 모든 길이에 대하여 몇가지 방법으로 만들 수 있는 지 도출
		for (int i = 0; i < pipeTypeCnt; i++) {
			int pipeLen = pipes[i][0];
			int pipeCnt = pipes[i][1];
			
			for (int j = goal; j >= pipeLen; j--) {
				for (int k = 1; k <= pipeCnt; k++) {
					if (j - k*pipeLen>= 0) {
						dp[j] += dp[j - k*pipeLen];
					} else {
						break;
					}
				}
			}
		}
		
		System.out.println(dp[goal]);
		
		
		
	}
	



}