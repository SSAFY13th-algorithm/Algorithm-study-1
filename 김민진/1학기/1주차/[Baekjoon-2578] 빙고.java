import java.io.*;
import java.util.*;

public class Main {

	static int	bingoLines = 0;
	static int	numberCounted = 0;

	static int[][]	bingo = new int[5][5];

	static BufferedReader	br;
	static StringTokenizer	st;

	public static void main(String[] args) throws IOException {

		init();
		bingo();
	}

	static void	init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 5; j++) {
				bingo[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void	bingo() throws IOException {
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 5; j++) {
				int target = Integer.parseInt(st.nextToken());

				numberCounted++;
				if (findAndMarkVisited(target)) {
					System.out.println(numberCounted);
					return ;
				}
			}
		}
	}

	static boolean	findAndMarkVisited(int target) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (bingo[i][j] == target) {
					bingo[i][j] = -1;

					isBingo(i, j);
					// 여기 때문에 틀린듯.. 아제발
					if (bingoLines >= 3) {
						return true ;
					}
				}
			}
		}

		return false ;
	}

	static void	isBingo(int x, int y) {
		if (checkHorizontal(x)) bingoLines++;
		if (checkVertical(y)) bingoLines++;
		if (x == y && checkUpDiagonal()) bingoLines++;
		if (x + y == 4 && checkDownDiagonal()) bingoLines++;
	}

	static boolean	checkHorizontal(int x) {
		for (int i = 0; i < 5; i++) {
			if (bingo[x][i] != -1) return false;
		}

		return true;
	}

	static boolean	checkVertical(int y) {
		for (int i = 0; i < 5; i++) {
			if (bingo[i][y] != -1) return false;
		}

		return true;
	}

	static boolean	checkUpDiagonal() {
		for (int i = 0; i < 5; i++) {
			if (bingo[i][i] != -1) return false;
		}

		return true;
	}

	static boolean	checkDownDiagonal() {
		for (int i = 0; i < 5; i++) {
			if (bingo[i][4-i] != -1) return false;
		}

		return true;
	}
}