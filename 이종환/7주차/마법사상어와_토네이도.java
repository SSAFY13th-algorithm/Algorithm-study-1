import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와_토네이도 {
	static int[] dy = {-1,0,1,0}; // 위 왼쪽 아래 오른쪽 순
	static int[] dx = {0,-1,0,1};
	static int[][] arr;
	static int front;
	static int side;
	static int size;
	static int originalAmount;
	static int blowAmount = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		size = Integer.parseInt(br.readLine());
		arr = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int y = size/2;
		int x = size/2;
		front = 1; // 처음에 왼쪽으로 이동
		
		// 움직임을 보면  특정 길이만큼 이동을 2번하면 이동거리가 1 늘어남
		int moveLen = 1; // 지금 방향으로 이동할 예정인 거리
		int curMove = 0; // 실제로 이동한 거리
		int direcChangeCnt = 0; // 방향이 변경된 횟수 
		while( y != 0 || x != 0 ) {
			//일단 방향으로 이동
			y += dy[front];
			x += dx[front];
			move(y,x);
			curMove++;
			
			if (curMove == moveLen) {
				// 이제 방향을 바꿔야함. 
				front = (front + 1)%4;
				curMove = 0;
				direcChangeCnt++;
				
				if (direcChangeCnt == 2) {
					moveLen++;
					direcChangeCnt = 0;
				}
				
			}
			
		}
		
		System.out.println(blowAmount);
		
	}
	
	private static void move(int y,int x ) { // 토네이도가 도착하는 곳의 좌표
		//다른 곳으로 다 날린 후 남아있는 녀석. 이는 나중에 alpha로 이동한다.
		originalAmount = arr[y][x]; //원래 양
		int leftAmount = arr[y][x];
		
		int right = (front+3)%4; // 가는 방향의 오른쪽 방향을 나타내는 인덱스
		int left = (front+1)%4; // 가는 방향의 왼쪽 방향을 나타내는 인덱스
		
		side = left; // 목표 방향(왼 )
		leftAmount -= processSideAll(y,x);
		side = right; // 목표 방향(오)
		leftAmount -= processSideAll(y,x);
	
		//정면
		leftAmount -= processSandMove(y,x,5,2,0);
		
		// 남은거 alpha로
		int ty = y + dy[front];
		int tx = x + dx[front];
		if(check(ty,tx,leftAmount)) arr[ty][tx] += leftAmount;
		
		arr[y][x] = 0;
		
	}
	
	private static int processSideAll(int y, int x) {
		int totalMinus = 0;
		
		totalMinus += processSandMove(y,x,10,1,1);
		totalMinus += processSandMove(y,x,7,0,1); 
		totalMinus += processSandMove(y,x,2,0,2);
		totalMinus += processSandMove(y,x,1,-1,1);
		
		return totalMinus;
	}
	
	private static int processSandMove(int y, int x, int percent, int frontAmount, int sideAmount) {
		int moveAmount = (originalAmount * percent)/100;
		int ty = y + dy[front]*frontAmount + dy[side]*sideAmount;
		int tx = x + dx[front]*frontAmount + dx[side]*sideAmount;
		if(check(ty,tx,moveAmount)) arr[ty][tx] += moveAmount;
		
		return moveAmount;
	}
	
  
	private static boolean check(int y, int x, int amount) {
		if (y < 0 || x < 0 || y >= size || x >= size) {
			// 지도 밖으로 날아가버린 경우
			blowAmount += amount;
			return false;
		}
		return true;
	}
}
