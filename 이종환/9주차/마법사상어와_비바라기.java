import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 마법사상어와_비바라기 {
	// 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ + 0번추가
	static Set<Point> clouds = new HashSet<>();
	static Set<Point> increased = new HashSet<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dy = {0, 0,-1,-1,-1,0,1,1,1};
	static int[] dx = {0, -1,-1,0,1,1,1,0,-1};
	static int[][] arr;
	static int size;
	static int moveCnt;
	
	public static void main(String[] args) throws IOException {
		getInput();
		process();
		getResult();
	}

	private static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		moveCnt = Integer.parseInt(st.nextToken());
		arr = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	//  (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름 생성
		for (int i = 0; i <2; i++) for (int j = 0; j < 2; j++) clouds.add(new Point(i,size-1-j)); // 0~size-1인점 고려
	}
	
	private static void process() throws IOException {
		// TODO Auto-generated method stub
		for (int i = 0; i < moveCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int direction = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			move(direction,distance);
			duplicate();
			createCloud();	
		}
	}

	private static void move(int direction, int distance) {
		// 이동시 인덱스 넘어가면 1-> N , N -> 1처리. 하지만 인접한지 여부 판단할때는 x
		// 음수 안나오기 위한 +size*1000
		for ( Point cloud: clouds) {
			int tY = (cloud.y + dy[direction]*distance + size*1000)%size;
			int tX = (cloud.x + dx[direction]*distance + size*1000)%size;
			arr[tY][tX]++;
			increased.add(new Point(tX,tY));
		}
		clouds.clear();
	}
	
	private static void duplicate() {
		for (Point t: increased) {
			//대각선 -> 2,4,6,8번
			for (int i = 1; i <= 4; i++) {
				int nY = t.y + dy[i*2];
				int nX = t.x + dx[i*2];
				if (isValid(nY,nX)) arr[t.y][t.x]++;
			}
		}
		
	}
	
	private static boolean isValid(int nY, int nX) {
		if (nY < 0 || nX <0 || nY >= size || nX >= size || arr[nY][nX] == 0) return false;
		return true;
	}

	private static void createCloud() {
		//이전에 구름이 있던 곳 == 증가한 곳 -> 여기는 visited 처리
		boolean[][] preCloud = new boolean[size][size];
		for (Point t: increased) preCloud[t.y][t.x] = true;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (preCloud[i][j] || arr[i][j] < 2) continue;
				arr[i][j] -=2;
				clouds.add(new Point(j,i));
			}
		}
		increased.clear(); // 초기화
	}

	private static void getResult() {
		int sum = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sum += arr[i][j];
			}
		}
		System.out.println(sum);
	}
}
