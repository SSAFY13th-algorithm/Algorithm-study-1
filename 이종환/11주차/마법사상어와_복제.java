import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 마법사상어와_복제 {
	
	static final int SIZE = 4;
	static int[][] smell = new int[SIZE][SIZE];
	static int[] dy = {0,-1,-1,-1,0,1,1,1}; 
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] sharkDy = {-1,0,1,0} ;// 상 좌 하 우 
	static int[] sharkDx = {0,-1,0,1 }; // 상 좌 하 우 
	static int[] bestMovement = new int[3];
	static int ans,totalMagicCnt,magicCnt = 0;
	static ArrayList<ArrayList<ArrayList<Fish>>> sea = new ArrayList<>();
	static Queue<Fish> exRound = new LinkedList<>();
	static Queue<Fish> movementResult= new LinkedList<>();
	static Fish shark;
	
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}

	private static void print() {System.out.println(ans);}

	private static void process() {
		
		while(magicCnt++ < totalMagicCnt) {
			copyFish(); // 1번 
			moveFishes(); // 2번 
			moveShark(); // 3번 
			addFish();// 4번 
		}
		getLeft();
		
	}
	


	private static void copyFish() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ArrayList<Fish> block = sea.get(i).get(j);
				for (Fish f: block) exRound.add(f);
			}
		}
	}
	
	private static void moveFishes() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ArrayList<Fish> block = sea.get(i).get(j);
				for (Fish f: block) {
					movementResult.add(moveFish(f));
				}
				block.clear();
			}
		}
		
		
		while(!movementResult.isEmpty()) {
			Fish f = movementResult.poll();
			sea.get(f.y).get(f.x).add(f);
		}
	}


	private static Fish moveFish(Fish f) {
		int dir;
		for (int i = 0; i < 8; i++) {
			dir = (8+f.dir - i)%8;
			int ny = f.y + dy[dir];
			int nx = f.x + dx[dir];
			
			if (isInvalid(ny,nx)) continue;
			
			return new Fish(ny,nx,dir);
		}
		
		return f;
	}

	private static void moveShark() {
		int maxKillCnt = -1;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					int killCnt = getKillCnt(i,j,k);
					if ( killCnt > maxKillCnt) {
						maxKillCnt = killCnt;
						bestMovement[0] = i;
						bestMovement[1] = j;
						bestMovement[2] = k;
					}
				}
			}
		}
		executeMovement();
		
		
	}

	private static int getKillCnt(int i, int j, int k) {
		int killCnt =0;
		int y = shark.y + sharkDy[i];
		int x = shark.x + sharkDx[i];
		
		if (OOB(y,x)) return -1;
		killCnt += sea.get(y).get(x).size();
		
		y += sharkDy[j];
		x += sharkDx[j];
		
		if (OOB(y,x)) return -1;
		killCnt += sea.get(y).get(x).size();
		
		y += sharkDy[k];
		x += sharkDx[k];
		
		if (OOB(y,x)) return -1;
		if (y == shark.y + sharkDy[i] && x == shark.x + sharkDx[i]) return killCnt;
		killCnt += sea.get(y).get(x).size();
		
		return killCnt;
	}
	
	private static void executeMovement() {
		int y = shark.y;
		int x = shark.x;
		
		for (int i = 0; i < 3; i++) {
			y += sharkDy[bestMovement[i]];
			x += sharkDx[bestMovement[i]];
			if (sea.get(y).get(x).size() != 0) {
				sea.get(y).get(x).clear();
				smell[y][x] = magicCnt;
			}
			
		}
		
		shark.y = y;
		shark.x = x;
		
	}

	private static void addFish() {
		while(!exRound.isEmpty()) {
			Fish f = exRound.poll();
			sea.get(f.y).get(f.x).add(f);
		}
		
	}
	
	private static void getLeft() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ans += sea.get(i).get(j).size();	
			}
		}
	}

	private static boolean OOB(int y, int x) {
		if ( y < 0 || x < 0 ||  y >= SIZE || x >= SIZE) return true; //격자 벗어남 
		return false;
	}
	
	// 그 방향으로 이동가능한 지 확인하는 
	private static boolean isInvalid(int y, int x) {
		if (OOB(y,x)) return true;
		if ( y == shark.y && x == shark.x) return true; // 상어 있음  
		if ( smell[y][x] + 2 >= magicCnt ) return true; // 냄새 남음 
		else return false;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 2차원 배열을 어레이리스트를 통해 구현 
		for (int i = 0; i < 4; i++) {
			sea.add(new ArrayList<>());
			for (int j = 0; j < 4; j++) {
				sea.get(i).add(new ArrayList<>()); 
				smell[i][j] = -100;
			}
		}
		int fishCnt = Integer.parseInt(st.nextToken());
		totalMagicCnt = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < fishCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken())-1;
			
			Fish f = new Fish(y,x,dir);
			sea.get(y).get(x).add(f);
		}
		st = new StringTokenizer(br.readLine());

		int y = Integer.parseInt(st.nextToken())-1;
		int x = Integer.parseInt(st.nextToken())-1;
		shark = new Fish(y,x,-1);
	} 
	
	
	
	static class Fish {
		int y;
		int x;
		int dir;
		public Fish(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
		
	}
	
	
}
