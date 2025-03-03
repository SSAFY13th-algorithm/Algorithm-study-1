import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬 {
	static boolean[][] copy;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int cnt = 0;
		
		boolean[][] arr = new boolean[height][width];
		
		for (int i = 0; i < height; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < width; j++) {
				arr[i][j] = input[j].equals("1");
			}
		}
		
		for (int i = 0; i < height; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < width; j++) {
				boolean temp = input[j].equals("1");
				arr[i][j] = (temp != arr[i][j]); // false면 원래와 그대로, true면 최소 1번 뒤집힘.			
			}
		}
		//위 로직 이후에는 두 개의 행렬에서 동일한 부분은 false, 다른 부분은 true로 되어있을 것음
		
		
		//아래의 2차원 배열을 위 arr과 동일하게 만드는 것이 목표
		copy = new boolean[height][width];
		
		for (int i = 0; i < height-2; i++) {
			for (int j = 0; j < width-2; j++) {
				// 어차피 한번에 3x3을 뒤집기에 이런식으로 범위를 줄여 탐색
				if (copy[i][j] != arr[i][j]) {
					flip(i,j);
					cnt++;
				}
			}
		}
		
		loop:
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (copy[i][j] != arr[i][j]) {
					cnt = -1;
					break loop;
				}
			}
		}
		
		System.out.println(cnt);
		
		
		
	}
	
	static void flip(int y, int x) {
		for (int i = 0; i <3; i++) {
			for (int j = 0; j < 3; j++) {
				copy[y+i][x+j] = !copy[y+i][x+j]; // 기존의 반대
			}
		}
	}
	
	
}
