import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb;

	public static int ans;
	
	public static int[] dx = {-1,-1,0,1,1,1,0,-1};
	public static int[] dy = {0,-1,-1,-1,0,1,1,1};
	public static List<Node> fishes = new ArrayList<>();
	public static Fish[][] arr = new Fish[5][5];

	public static boolean OOB(int x, int y) {
		if(x > 4 || x < 1 || y > 4 || y < 1) return true;
		return false;
	}
	
	
	public static class Node{
		int x,y;
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static class Fish{
		int num;
		int dir;
		public Fish(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
	
	void swapFish(Fish x, Fish y) {
		
	}
	
	public static void init() throws IOException {
		for(int i = 1; i <= 4; i++) {
			int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int j = 0; j < 8; j+= 2) {
				int num = in[j];
				int dir = in[j + 1];
				int x = i;
				int y = (j / 2) + 1;
				arr[x][y] = new Fish(num,dir - 1);
			}
		}
	}
	public static Node findIdx(int num, Fish tmpArr[][]) {
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				if(tmpArr[i][j].num == num) return new Node(i,j);
			}
		}
		return new Node(-1,-1);
	}
	public static void moveFish(int sX,int sY, Fish tmpArr[][]) {
		for(int i = 1; i <= 16; i++) {
			Node cur = findIdx(i, tmpArr);
			if(cur.x != -1) {
				int dir = tmpArr[cur.x][cur.y].dir;
				for(int j = 0; j < 8; j++) {
					int nd = (dir + j) % 8;
					int nx = cur.x + dx[nd];
					int ny = cur.y + dy[nd];
					if(OOB(nx,ny) || (nx == sX && ny == sY)) continue;
					tmpArr[cur.x][cur.y].dir = nd;
					Fish tmp = tmpArr[cur.x][cur.y];
					tmpArr[cur.x][cur.y] = tmpArr[nx][ny];
					tmpArr[nx][ny] = tmp;
					break;
				}
			}
		}
	}
	
	public static void solve(int x, int y, int cnt, Fish firstArr[][]) {
		Fish tmpArr[][] = new Fish[5][5];	
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				Fish tmp = firstArr[i][j];
				tmpArr[i][j] = new Fish(tmp.num, tmp.dir);
			}
		}
		moveFish(x,y,tmpArr);
		
		int dir = tmpArr[x][y].dir;
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		boolean succ = false;
		while(!OOB(nx,ny)) {
			if(tmpArr[nx][ny].num != 0) {
				succ = true;
				int num = tmpArr[nx][ny].num;
				tmpArr[nx][ny].num = 0;
	            solve(nx,ny,cnt + num, tmpArr);
				tmpArr[nx][ny].num = num;
				
			}
	        nx += dx[dir];
	        ny += dy[dir];
		}
	    if(!succ) {
	        ans = Math.max(ans,cnt);
	        return;
	    }
	}
	
	public static void solution() throws IOException{
		init();
		int num = arr[1][1].num;
		arr[1][1].num = 0;
	    solve(1,1,num,arr);
	    System.out.println(ans);
		
	}
	public static void main(String[] args) throws IOException {
		solution();
	}
}
