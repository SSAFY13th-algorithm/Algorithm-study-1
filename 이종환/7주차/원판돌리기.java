import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 원판돌리기 {
	//문제 정리
	//1. 원판 하나당 1차원 배열 하나. 이를 통해 인접여부를 따질것임.
	//2. 원 조회는 인덱스 조작을 통해 문제 없음
	//2. 매초 완탐: O(N*M*T) -> 50^3 = 125000: 여유로움;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	static int[][] arr;
	static int plateCnt;
	static int plateSize;
	static int sum = 0;
	static int numCnt = 0;
	static int target =0;
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		plateCnt = Integer.parseInt(st.nextToken());
		plateSize = Integer.parseInt(st.nextToken());
		numCnt = plateCnt * plateSize; // 처음에는 전부 숫자로 차있을거기에
		int totalTime = Integer.parseInt(st.nextToken());
		
		arr = new int[plateCnt][plateSize];
		
		//원판 숫자 입력받음
		for (int i = 0; i < plateCnt; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < plateSize; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				sum +=arr[i][j];
			}
		}
		
		for (int time = 1; time <= totalTime; time++) {
			st = new StringTokenizer(br.readLine());
			int multiply = Integer.parseInt(st.nextToken());
			int direction = (st.nextToken().equals("0"))?1:-1; // 시계 반시계를 위해 임의 변형
			int amount = Integer.parseInt(st.nextToken());
			
			for (int target = multiply-1; target <plateCnt; target += multiply ) {
				// 새롭게 만들어서 갈아끼우는 방식
				int[] nPlate = new int[plateSize]; 
				for (int i = 0; i < plateSize; i++) {
					int targetIdx = (i + direction*amount + plateSize*100) % plateSize; // 회전 구현
					nPlate[targetIdx] = arr[target][i];
				}
				// 회전 완료한 배열을 대입
				arr[target] = nPlate;  
			}
			// 이제 같은거 있나 확인
			process();
		}
		System.out.println(sum);
	}
	
	private static void process() {
		int preNumCnt = numCnt;
		
		for (int i = 0; i < plateCnt; i++) {
			for (int j = 0; j < plateSize; j++) {
				//0이면 숫자가 제거된 자리
				if (arr[i][j] == 0 )continue;
				
				target = arr[i][j];
				if(checkAll(i,j)) bfs(i,j);
			}
		}
		
		
		//같음 -> 인접한 숫자가 없었다는 의미
		// 평균 기준 +- 진행
		if (numCnt == preNumCnt) {
			// 여기서 값을 복사해서 이 값으로 비교해야함
			// 아니면 비교 도중에 sum값이 계속해서 바뀌기에 문제 발생.
			int curSum = sum;
			for (int i = 0; i < plateCnt; i++) {
				for (int j = 0; j < plateSize; j++) {
					if (arr[i][j] == 0 )continue;

					if (arr[i][j]*numCnt < curSum) {
						arr[i][j]++;
						sum++;
					} else if (arr[i][j] * numCnt > curSum) {
						arr[i][j]--;
						sum--;
					}
					
				}
			}
		}
		
	}
	
	private static boolean checkAll(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int ty = y + dy[i];
			int tx = (x + dx[i] + plateSize)%plateSize; // 이걸로 원 구현
			if (check(ty,tx)) return true;
		}
		return false;		
	}
	
	private static boolean check(int ty,int tx) {
		if (ty <0 || tx < 0 || ty >= plateCnt || tx >= plateSize 
					|| arr[ty][tx] != target) return false;
		return true; // 이 방향에  이어지는 것이 있다는 뜻;
	}
		
	
	
	private static void bfs(int y, int x) {
		arr[y][x] = 0;
		numCnt--;
		sum -= target;
		
		q.add(new Point(x,y));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int ty = p.y;
			int tx = p.x;
			for (int i = 0; i < 4; i++) {
				int ny = ty + dy[i];
				int nx = (tx + dx[i] + plateSize)%plateSize;
				if (check(ny,nx)) {
					arr[ny][nx] = 0;
					numCnt--;
					sum -= target;
					
					q.add(new Point(nx,ny));
				}
			}
			
		}
	}
	
}
