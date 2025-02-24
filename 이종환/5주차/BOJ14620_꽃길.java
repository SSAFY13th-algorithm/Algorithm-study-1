import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int size;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		
		
		arr = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int min = 1000000000;
		
		//편의를 위하여 인덱스를 1차원화
		int sizeSquare = size*size;
		
		for (int i = 0; i < sizeSquare; i++) {
			for (int j = i+1; j < sizeSquare; j++) {
				for (int k = j+1; k < sizeSquare; k++ ) {
					boolean[][] flowers = new boolean[size][size];
					if (check(i,flowers) && check(j,flowers) && check(k,flowers)) {
						int sum = getSum(i) + getSum(j) + getSum(k);
						min = Math.min(sum, min);
					}
				}
			}
		}
		
		System.out.println(min);


	}
	public static int getSum(int index) {
		int y = index/size;
		int x = index%size;
		
		int sum = 0;
		
		sum += arr[y][x];
		for (int i = 0; i < 4; i++) {
			int targetY = y + dy[i];
			int targetX = x + dx[i];
			sum += arr[targetY][targetX];
		}
		return sum;
	}
	

	public static boolean check(int index, boolean[][] flowers) {
		// 범위를 벗어났거나 이미 그곳에 꽃이 있는 경우를 확인
		int y = index/size;
		int x = index%size;
		
		if ( flowers[y][x]) {
			return false;
		}
		
		for (int i = 0; i < 4; i++) {
			int targetY = y + dy[i];
			int targetX = x + dx[i];
			if (targetY < 0 || targetX < 0 || targetY >= size 
					|| targetX >= size || flowers[targetY][targetX]) {
				return false;
			}
		}
		// 맞다면 true처리 후 합 계산해서 리턴
		
		flowers[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int targetY = y + dy[i];
			int targetX = x + dx[i];
			flowers[targetY][targetX] = true;
		}

		return true;
	}
}
