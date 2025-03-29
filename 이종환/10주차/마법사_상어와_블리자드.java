import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사_상어와_블리자드 {
	static Point[] seatNum;
	static int[] dy = {0,1,0,-1}; // 좌하우상 순
	static int[] dx = {-1,0,1,0};
	static int[] convertDir = {-1,3,1,0,2}; // 입력한 마법의 방향을 이를 통해 캐스팅
	static int[] boomCnt = {0,0,0,0};
	static int[][] numArr; // 좌석번호 적힌 2차원 배열
	static int[][] arr;
	
	
	static int maxNum;
	static int size;
	static int magicCnt;
	
	
	static BufferedReader br;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		getInput();
		setSeatNums();
		process();
		calculate();
	}

	private static void getInput() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		size = Integer.parseInt(st.nextToken());
		maxNum = size*size-1;
		magicCnt = Integer.parseInt(st.nextToken());
		seatNum = new Point[maxNum+1]; // 대입 시 편의성을 위한 +1;
		numArr = new int[size][size];
		arr = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static void setSeatNums() {
		int y = size/2;
		int x = size/2;
		int direction = 0;
		int len = 1;
		int num = 1;
		while(true) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < len; i++) {
					if (num > maxNum) return;
					y += dy[direction];
					x += dx[direction];
					numArr[y][x] = num;
					seatNum[num++] = new Point(x,y);
				}
				direction = (direction+1)%4;
			}
			len++;
		}
	}



	private static void process() throws IOException {
		
		for (int i = 0; i < magicCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int direction = convertDir[Integer.parseInt(st.nextToken())];
			int len = Integer.parseInt(st.nextToken());
			
			breakBeads(direction,len);
			moveBeads();
			
			while(explode() != 0) {
				moveBeads();
			}
			duplicate();
		}

	}
	private static void duplicate() {
		Queue<Integer> q = new LinkedList<>();
		int serialNum = -1; // 지금 연속되는 숫자
		int serialCnt = -1; // 연속된 횟수
		for (int i = 1; i <= maxNum; i++) {
			Point t = seatNum[i];
			int beadNum = arr[t.y][t.x];
			
			if (beadNum == 0)break;
			if (beadNum == serialNum) {
				serialCnt++;
				continue;
			} 
			
			if (serialNum != -1) {
				q.add(serialCnt);
				q.add(serialNum);
			}

			serialNum = beadNum;
			serialCnt = 1;
		}
		if (serialNum != -1) {
			q.add(serialCnt);
			q.add(serialNum);
		}

		
		for (int i = 0; i < size; i++) Arrays.fill(arr[i], 0);
		
		int num = 1;
		while(!q.isEmpty() &&  num < maxNum) {
			Point t = seatNum[num++];
			arr[t.y][t.x] = q.poll();
		}
	}

	private static void breakBeads(int direction, int len) {
		int y = size/2;
		int x = size/2;
		for (int i = 0; i < len; i++) {
			y += dy[direction];
			x += dx[direction];
			arr[y][x] = 0;
		}
	}

	private static void moveBeads() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < maxNum; i++) {
			Point target = seatNum[i];
			if (arr[target.y][target.x] != 0) q.add(arr[target.y][target.x]);
		}
		
		for (int i = 0; i < size; i++) Arrays.fill(arr[i], 0);
		
		int num = 1;
		while(!q.isEmpty()) {
			int beadNum = q.poll();
			Point target = seatNum[num++];
			arr[target.y][target.x] = beadNum;
		}
	}

	
	private static int explode() { // 폭발로 사라진 만큼 리턴
		int broken = 0;
		int serialNum = -1; // 지금 연속되는 숫자
		int serialCnt = -1; // 연속된 횟수
		int breakPoint = maxNum+1;
		for (int i = 1; i <= maxNum; i++) {
			Point t = seatNum[i];
			int beadNum = arr[t.y][t.x];
			
			if (beadNum == 0) {
				breakPoint = i;
				break;
			}
			
			if (beadNum == serialNum) {
				serialCnt++;
				
				continue;
			} 

			if(serialCnt >= 4) broken += turnZero(i-1, serialCnt);
			serialNum = beadNum;
			serialCnt = 1;
		}
		
		if (serialNum != 0 && serialCnt >=4) broken += turnZero(breakPoint-1,serialCnt);
		
		return broken;
	}


	private static int turnZero(int end,int serialCnt) {
		Point t = seatNum[end];
		boomCnt[arr[t.y][t.x]] += serialCnt;
		
		for (int i = 0; i < serialCnt; i++) {
			t = seatNum[end-i];
			arr[t.y][t.x] = 0;
		}
		return serialCnt;
	}
	
	private static void calculate() {
		int ans = 0;
		for (int i = 1; i <= 3; i++) {
			ans += i*boomCnt[i];
		}
		System.out.println(ans);
	}
	


}
