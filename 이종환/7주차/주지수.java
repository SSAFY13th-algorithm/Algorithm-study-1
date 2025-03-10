import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 주지수 {
	public static void main(String[] args) throws IOException {
		
		//출입력이 많기에 BufferedReader/Writer 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		
		int[][] arr =new int[height+1][width+1]; // 실제 인풋값 저장
		int[][] cumulative = new int[height+1][width+1];// 인덱스 00부터 i,j까지의 합 저장
		int[][] targetArr = new int[height+1][width+1]; // 직사각형 합 저장
		
		
		int sum = 0;
		for (int i = 1; i <= height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= width; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				sum += arr[i][j];
				cumulative[i][j] = sum;
			}
		}
		
		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++) {
				targetArr[i][j] = targetArr[i-1][j] + cumulative[i][j] - cumulative[i-1][width];
			}
		}
		
		
		int orderCnt = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < orderCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int stY = Integer.parseInt(st.nextToken());
			int stX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			
			int ans = targetArr[endY][endX] - targetArr[endY][stX-1] - targetArr[stY-1][endX] 
					+ targetArr[stY-1][stX-1];
			
			bw.append(ans+"\n");
		}
		bw.flush();
		bw.close();
	}
}
