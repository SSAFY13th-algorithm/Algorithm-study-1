import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ19237_어른상어 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int size = Integer.parseInt(st.nextToken());
		int sharkCnt = Integer.parseInt(st.nextToken());
		int smellTime = Integer.parseInt(st.nextToken());
		
		
		int[] dy = {0, -1,1,0,0}; // 편의를 위해 0번인덱스에 더미값 추가
		int[] dx = {0,0,0,-1,1}; // 동일한 방식 
		
		//상어 위치 표
		int[][] arr =new int[size][size];
		
		//상어 냄새 == 과거의 상어 위치. 즉 과거를 저장할 곳이 필요함
		int [][][] past = new int[smellTime][size][size];

		
		int[] sharkY = new int[sharkCnt+1]; // 각 상어의 y좌표
		int[] sharkX = new int[sharkCnt+1]; // 각 상어의 x좌표
		//쫒겨나지 않은 상어 저장.
		Set<Integer> survive = new HashSet<Integer>();
		for (int i = 1; i <= sharkCnt; i++) {
			survive.add(i);
		}
		
		//상어가 현재 바라보는 방향 저장
		int[] sharkCurDir = new int[sharkCnt+1];
		
		// 상어마다 방향의 우선순위를 기록
		// depth 1은 1~sharkCnt까지 이용 -> i번 
		// depth 2은 1~4까지 이용
		// [i][j][k] -> i번 상어가 j번 방향을 보고 있을 때 k번째 우선순위
		int[][][] sharkDirections = new int[sharkCnt+1][5][5];
		

		
		// 초기 값 받음.
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0) {
					//상어라면 위치값 정함
					sharkY[arr[i][j]] = i;
					sharkX[arr[i][j]] = j;
				}
			}
		}
		
		// 각각의 상어 바라보고 있는 방향 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= sharkCnt; i++) {
			sharkCurDir[i] = Integer.parseInt(st.nextToken());
		}
		
		//상어마다 방향 선호도 저장
		for (int i = 1; i<= sharkCnt; i++) {
			
			for (int j = 1; j<= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= 4; k++) {
					sharkDirections[i][j][k] = Integer.parseInt(st.nextToken());
				}
				
			}
		}
		
		int time = 0;
		
		//여기까지가 초기입력. 이제 while문 돌려야겠죠?
		while(survive.size() != 1 && time <= 1000) {
			// 과거 가장 오래된걸 날리고 새롭게 arr의 주소를 새롭게 큐에 넣음
			past[time % smellTime] = arr;

			
			//반복문 한번 돌릴때마다 새로운 것 생성.
			int[][] curArr = new int[size][size];
			
			List<Integer> aliveSharks = new ArrayList<Integer>();  
			for (int shark: survive) {
				aliveSharks.add(shark);
			}

			for (int i = 0; i < aliveSharks.size(); i++) {
				int shark = aliveSharks.get(i);
				if (!survive.contains(shark)) continue;
				
				boolean findMySmell = false;
				int nextMove = -1; // 이동할 방향 저
				int y = sharkY[shark];
				int x = sharkX[shark];
				int dir = sharkCurDir[shark];
				
				// 우선 비어있는 곳 있는 지 확인
				for (int j =1; j<= 4; j++) {
					// 바라볼 방향 정함
					// 이 값을 통해 dx,dy 접근
					int idx = sharkDirections[shark][dir][j]; 
					int targetY = y + dy[idx];
					int targetX = x + dx[idx];
					if (targetY < 0 || targetX < 0 || 
							targetY >= size || targetX >= size) {
						continue;
					}
					
					//인덱스 벗어나지 않는 것을 확인했으면 과거와 비교.
					boolean isClean = true;
					for (int k = 0; k < smellTime; k++) {
						if (past[k][targetY][targetX] != 0) {
							isClean = false;
							
							if (!findMySmell && past[k][targetY][targetX] == shark) {
								// 처음으로 검사해서 걸린 곳에 이
								findMySmell = true;
								nextMove = idx;
							}
						}
						
					}
					
					if (isClean) { // 모든 과거에 다 클린하면 for문 탈출
						nextMove = idx;
						break;
					} 
					
					
				}
				 
				// 상어 이동   
				int nextY = y + dy[nextMove];
				int nextX = x + dx[nextMove];
				sharkCurDir[shark] = nextMove; 
				
				if ( curArr[nextY][nextX] == 0) {
					curArr[nextY][nextX] = shark;
				} else {
					if (shark < curArr[nextY][nextX]) {
						survive.remove(curArr[nextY][nextX]);
					} else {
						survive.remove(shark);
					}
					curArr[nextY][nextX] = Math.min(shark, curArr[nextY][nextX]);
				}
				
				sharkY[shark] = nextY;
				sharkX[shark] = nextX;
			}
			
			
			//그리고 curArr의 주소를 arr에 할당
			arr = curArr;
			time++;
			
		}
		
	
		if (time == 1001) {
			System.out.println(-1);
		} else {
			System.out.println(time);
		}


		
	}
}
