import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 어항정리 {
	

	static int size,tryCnt,goalDiff;
	static int height,cnt,floor,stIdx;
	static int[][] folded;
	static int[][] temp;
	static int[] arr;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}

	private static void print() {System.out.println(tryCnt);}

	private static void process() {
		while(!isEnd()) {
			tryCnt++;
			plusSmallest();
			foldOne();
			foldHalf();
		}
	}

	private static void plusSmallest() {
		int smallest = Integer.MAX_VALUE;
		ArrayList<Integer> idxes = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (arr[i] == smallest) idxes.add(i);
			else if (arr[i] < smallest) {
				idxes.clear();
				smallest = arr[i];
				idxes.add(i);
			}
		}
		for (int i = 0; i < idxes.size(); i++) {
			arr[idxes.get(i)]++;
		}
		
	}


	private static void foldOne() {
		folded = new int[size][size];
		height=1; //기둥 높이. 처음에는 높이1짜리 기둥이 존재한다고 가정
		floor=1; //바닥 높이.
		cnt = 1; // 기둥 개수
		stIdx = 0;
		
		for (int i = 0; i < size; i++) folded[0][i] = arr[i];
		while(isValid()) {
			int nIdx = stIdx+cnt; // 기둥 말고 바닥의 첫번째 인덱스
			for (int j = 0; j < cnt; j++) {
				for (int i = 0; i < height; i++) {
					folded[cnt-j+floor-1][nIdx+i] = folded[i][j+stIdx];
					folded[i][j+stIdx] = 0;
				}
			}
			stIdx = height*cnt; // 이젠 nIdx가 첫번째 기둥의 위치가 됨
			if(height == cnt)height++;
			else cnt++;
		}
		redistributeAll();
	}
	
	
	private static boolean isValid() {
		if (height == cnt) return size >= (height+1)*cnt;
		return size >= height*(cnt+1);
	}
	

	private static void foldHalf() {
		folded = new int[size][size];
		// 4등분 했을 때, 각각으 파트가 시작하는 인덱스
		int[] pIdx = {0, size/4,(size/4)*2,(size/4)*3};
		int quater = size/4;
		for (int i = 0; i < quater; i++) {
			folded[0][pIdx[3]+i] = arr[pIdx[3]+i];
			folded[1][pIdx[3]+i] = arr[pIdx[1]-1-i];
			folded[2][pIdx[3]+i] = arr[pIdx[1]+i];
			folded[3][pIdx[3]+i] = arr[pIdx[3]-1-i];
		}
		
		redistributeAll();		
	}

	
	//전체 재분배
	private static void redistributeAll() {
		temp = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				redistribute(i,j);
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// 변화량을 folded에 반영
				folded[i][j] += temp[i][j];
			}
		}

		sort();
	}
	
	private static void redistribute(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if ( ny < 0 || nx <0 || ny >= size|| nx >= size)continue;
			if (folded[ny][nx] == 0 || folded[y][x] <= folded[ny][nx])continue;
			
			//재분배양
			int amount = (folded[y][x] - folded[ny][nx])/5;
			temp[y][x] -= amount;
			temp[ny][nx] += amount;
		}
		
	}

	// 쌓여있는것을 원래대로.
	private static void sort() {
		int arrIdx = 0;
		
		for (int i = 0; i < size; i++) {
			if (arrIdx == size) break;
			if (folded[0][i] == 0) continue; 
			int idx = 0;
			while(true) {
				arr[arrIdx++] = folded[idx++][i];
				if(idx >= size || folded[idx][i] == 0) break;
			}
		}
	}
	
	private static boolean isEnd() {
		int largest = Integer.MIN_VALUE;
		int smallest = Integer.MAX_VALUE;
		for (int i = 0; i < size; i++) {
			largest = Math.max(largest, arr[i]);
			smallest = Math.min(smallest, arr[i]);
		}
		return largest-smallest <= goalDiff;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		size = Integer.parseInt(st.nextToken());
		goalDiff = Integer.parseInt(st.nextToken());
		tryCnt = 0;
		arr = new int[size];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
}
