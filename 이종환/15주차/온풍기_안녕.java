import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 온풍기_안녕 {
	
	static int[][] arr;
	static boolean[][][] walls; // 벽만 기록
	static boolean[][] visited;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[] mapping = {-1,1,3,0,2}; // 인풋과 방향 매핑
	static int width, height,goal,wallCnt,chocolate = 0;
	
	static ArrayList<Point> targets = new ArrayList<>();
	static ArrayList<Heater> heaters = new ArrayList<>();
	
	static class Heater{
		int y,x,dir;

		public Heater(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		process();

		print();
	}

	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());
		
		arr = new int[height][width];
		walls = new boolean[height][width][4];
		
		// 히터 및 조사 위치 파악
		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 5)targets.add(new Point(j,i)); 
				else if (num > 0) heaters.add(new Heater(i,j,mapping[num]));
			}
		}
		//
		// 벽 정보
		wallCnt = Integer.parseInt(br.readLine());
		for (int i = 0;i < wallCnt; i++) {
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int type = Integer.parseInt(st.nextToken());
			// i,j에서 어느방향으로 나아가려고 할 때 벽이 존재하는지 기록
			if (type == 0) {
				walls[y][x][0] = true;
				walls[y-1][x][2] = true;
			}
			if (type == 1) {
				walls[y][x][1] = true;
				walls[y][x+1][3] = true;
			}	
		}
	}
	

	private static void process() {
		while(true) {
			heat();
			redistribute();
			reduce();
			chocolate++;
			if(isFinish()) return;
		}
		
	}
	

	private static void test() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------------");
		
	}


	private static void heat() {
		for (Heater h:heaters) {
			visited = new boolean[height][width];
			int ny = h.y + dy[h.dir];
			int nx = h.x + dx[h.dir];
			
			visited[ny][nx] = true;
			wind(5,ny,nx,h.dir);
		}
	}


	private static void wind(int power, int y, int x, int dir) {
		arr[y][x] += power;
		if (power == 1) return;
		
		int[] side = {(dir+3)%4,(dir+1)%4};
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		if (!OOB(ny,nx) && !walls[y][x][dir] && !visited[ny][nx]) {

			visited[ny][nx] = true;
			wind(power-1,ny,nx,dir);
		}
		//대각선 바람 처리
		for (int s: side) {
			if(walls[y][x][s]) continue;
			ny = y + dy[s];
			nx = x + dx[s];
			
			if (OOB(ny,nx) || walls[ny][nx][dir]) continue;
			
			ny += dy[dir];
			nx +=  dx[dir];
			if (OOB(ny,nx) ||visited[ny][nx])continue;
			visited[ny][nx] = true;
			
			wind(power-1,ny,nx,dir);
		}
	}


	private static boolean OOB(int y, int x) {
		if (y <0 || x<0 || y >= height || x >= width ) return true;
		return false;
	}


	private static void redistribute() {
		int[][] nArr =new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int y = i, x = j;
				for (int k = 0; k < 4; k++) {
					if (walls[y][x][k]) continue;
					int ny = y + dy[k];
					int nx = x + dx[k];
					
					if (OOB(ny,nx))continue;
					if (arr[y][x] <= arr[ny][nx]) continue;
					int change = (arr[y][x] - arr[ny][nx])/4;
					nArr[y][x] -= change;
					nArr[ny][nx] += change;
				}
			}
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				arr[i][j] += nArr[i][j];
			}
		}
	}

	private static void reduce() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((i == 0 || j ==0 || i == height-1 || j == width-1) 
						&& arr[i][j] >0 ) arr[i][j]--;
			}
		}
		
	}

	private static boolean isFinish() {
		if (chocolate > 100) return true;
		for (Point p :targets) {
			if (arr[p.y][p.x] < goal) return false;
		}
		return true;
	}


	private static void print() {System.out.println(chocolate);}
}
