package baekjoon;

import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static int arr[][] = new int[6][6];
	public static int cnt = 0;
	
	public static boolean leftDown = false;
	public static boolean rightDown = false;
	
	public static boolean isEnd = false;
	
	public static void init() {
		for(int i = 1; i <= 5; i++) {
			for(int j = 1; j <= 5; j++) {
				arr[i][j] = scanner.nextInt();
			}
		}
	}
	public static class Node{
		int x,y;
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static Node deleteBingo(int x) {
		for(int i = 1; i <= 5; i++) {
			for(int j = 1; j <= 5; j++) {
				if(arr[i][j] == x) {
					arr[i][j] = 0;
					return new Node(i,j);
				}
			}
		}
		return new Node(1,1);
	}
	
	public static void countLD() {
		for(int i = 5; i > 0; i--) {
			if(arr[6 - i][i] != 0) return;
		}
		cnt++;
		leftDown = true;
	}
	
	public static void countRD() {
		for(int i = 1; i <= 5; i++) {
			if(arr[i][i] != 0) return;
		}
		cnt++;
		rightDown = true;
	}
	
	public static void checkBingo(int x, int y) {
		boolean succ = true;
		for(int i = 1; i <= 5; i++) {
			if(arr[x][i] != 0) {
				succ = false;
				break;
			}
		}
		if(succ) cnt++;
		succ = true;
		for(int i = 1; i <= 5; i++) {
			if(arr[i][y] != 0) {
				succ = false;
				break;
			}
		}
		if(!leftDown) countLD();
		if(!rightDown) countRD();
		if(succ) cnt++;
	}
	public static void solution() {
		init();
		for(int i = 0; i < 25; i++) {
			int x = scanner.nextInt();
			Node node = deleteBingo(x);
			checkBingo(node.x, node.y);
			if(!isEnd && cnt >= 3) {
				isEnd = true;
				System.out.println(i + 1);
			}
		}
	}
	
	public static void main(String[] args) {
		solution();
	}
}