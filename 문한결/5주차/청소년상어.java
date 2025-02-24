import java.util.*;
import java.io.*;

// 1. 상어는 0,0에서 물고기를 먹고 출발.
// 2. 물고기가 이동한다.
//    문제 설명에서 1부터 물고기가 움직인다.-> map을 이용해서 key를 물고기 value를 물고기의 위치로 넣어두고 하면될듯?
//    해당 방향에 물고기가 있거나, 빈칸이면 이동하거나 교환.없으면 45도 돌려야함 -> rotate함수 만약 한바퀴를 돌았는데도 없으면 그대로 두기
// 3. 상어가 이동한다.
//    상어는 해당 방향에서 모든 지점으로 갈 수 있음.-> dfs를 통해서 구현해라
//
//    
//
//
public class 청소년상어 {
	static int []dx=new int[] {0,-1,-1,-1,0,1,1,1},dy=new int[] {-1,-1,0,1,1,1,0,-1};
	static Fish[][] fishes=new Fish[4][];
	static int [] input;
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int answer=0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		for(int i=0;i<4;i++)
			fishes[i]=new Fish[4];
		for(int i=0;i<4;i++) {
			input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int j=0;j<8;j+=2) {
				fishes[i][j/2]=new Fish(input[j],input[j+1]-1);
			}
		}
		moveShark(0,0,0,fishes);
		System.out.println(answer);
	}
	static class Fish{
		int value;
		int dir;
		public  Fish(int value,int dir) {
			this.value=value;
			this.dir=dir;
		}
	    public Fish copy() {
            return new Fish(this.value, this.dir);
        }
	}
	static void moveShark(int x,int y,int sum,Fish[][]curMap) {
		
		 Fish[][] copyMap = new Fish[4][4];
	        
	        // 깊은 복사
	        for (int i = 0; i < 4; i++)
	            for (int j = 0; j < 4; j++)
	                copyMap[i][j] = curMap[i][j].copy();
		int temp=copyMap[y][x].value;
		int curDir=copyMap[y][x].dir;
		copyMap[y][x].value=0;
		moveFish(copyMap,x,y);
	
		for(int i=1;i<4;i++) {
			int xx=x+dx[curDir]*i;
			int yy=y+dy[curDir]*i;
			if(x+dx[curDir]*i>=0 && x+dx[curDir]*i<4 &&
					y+dy[curDir]*i>=0 && y+dy[curDir]*i<4 && copyMap[yy][xx].value>0)
			moveShark(x+dx[curDir]*i,y+dy[curDir]*i,sum+temp,copyMap);			
		}
		curMap[y][x].value=temp;
		answer=Math.max(answer, sum+temp);
		
		
	}
	static void moveFish(Fish[][]curFish, int curX,int curY) {
		Map<Integer,int []> maps=new HashMap();
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++)
			{
				if(curFish[i][j].value!=0)
				maps.put(curFish[i][j].value, new int[] {j,i});
			}
		}
		List<Integer> keys = new ArrayList<>(maps.keySet());
		keys.sort((a,b)->a-b);

		for (int k : keys) {
		    int yx[] = maps.get(k);
		    int x = yx[0], y = yx[1], dir = curFish[y][x].dir;
		
		    for (int i = 0; i < 8; i++) {
		        int nx = x + dx[(i + dir) % 8];
		        int ny = y + dy[(i + dir) % 8];
		      
		        if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && !(nx == curX && ny == curY) && curFish[ny][nx].value >= 0) {
		        	Fish temp = new Fish(curFish[ny][nx].value, curFish[ny][nx].dir);
		        	curFish[ny][nx] = new Fish(curFish[y][x].value, (i + dir) % 8);
		        	curFish[y][x] = new Fish(temp.value, temp.dir);
		           maps.put(curFish[y][x].value, new int[]{x, y});
		           
		           if(curFish[ny][nx].value>0)
		           maps.put(curFish[ny][nx].value, new int[]{nx, ny});
		        

		            break;
		        }
		    }
		}
	

		
	}

}
