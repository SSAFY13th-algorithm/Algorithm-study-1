package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2Matrix {
	
	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	private static int cnt;
	
	private static boolean[][] input;
	private static boolean[][] output;
	
	public static void main(String[] args) throws IOException {
		init();
		sol();
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = 0;
		
		input = readMatrix();
		output = readMatrix();
		
		br.close();
	}
	
	private static boolean[][] readMatrix() throws IOException {
		boolean[][] matrix = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();

			for (int j = 0; j < M; j++) {
				matrix[i][j] = s.charAt(j) == '1';
			}
		}
		
		return matrix;
	}
	
	private static void sol() {
		for (int i = 0; i < N-2; i++) {
			for (int j = 0; j < M-2; j++) {
				if (input[i][j] != output[i][j]) {
					flipFlop(i, j);
					cnt++;
				}
			}
		}
		
		System.out.println(checkSame() ? cnt : -1);
	}
	
	private static void flipFlop(int x, int y) {
		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				input[i][j] = !input[i][j];
			}
		}
	}
	
	private static boolean checkSame() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (input[i][j] != output[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

}
