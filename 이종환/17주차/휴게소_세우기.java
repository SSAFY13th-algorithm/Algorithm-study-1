import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 휴게소_세우기 {
	static PriorityQueue<Integer> points = new PriorityQueue<>();
	static int[] distance;
	static int pointCnt, plusCnt, len,ans,tempCnt;
	
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		pointCnt = Integer.parseInt(st.nextToken());
		plusCnt = Integer.parseInt(st.nextToken());
		len = Integer.parseInt(st.nextToken());
		distance = new int[pointCnt+1];
		ans = Integer.MAX_VALUE;
		
		points.add(0);
		points.add(len);
		
		if (pointCnt != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < pointCnt; i++) {
				points.add(Integer.parseInt(st.nextToken()));
			}
		}
	}
	
	private static void process() {
		changeToDistance();
		findMinMax();
	}
	
	private static void changeToDistance() {
		int pre = points.poll();
		
		for (int i = 0; i <= pointCnt; i++) {
			int cur = points.poll();
			distance[i] = cur - pre;
			pre = cur;
			
		}
	}
	
	private static void findMinMax() {
		int l = 1;
		int r = len+1;
		int mid = (l+r)/2; 
		while(l < r) {
			int cnt = getPlusCnt(mid);
			if (cnt > plusCnt) {
				// 목표보다 더 많이 세움 -> 설정길이가 너무 짧음. 더 늘려야함
				l = mid+1;
			} else if (cnt <= plusCnt) {
				// 목표보다 더 적게 세워지거나 같음 -> 설정길이를 더 줄여서 리트라이
				ans = Math.min(ans, mid);
				r = mid;
			}
			mid = (l+r)/2; 
		}
	}

	//  최댓값이 num일때 추가로 세워야하는 휴게소 개수 반환
	private static int getPlusCnt(int num) {
		tempCnt = 0;
		for (int i = 0; i <= pointCnt; i++) {
			if (distance[i] >= num) {
				tempCnt += distance[i]/num;
				if (distance[i]%num == 0) tempCnt--;
			}
			
		}
		//세워야하는 휴게소 개수가 목표보다 많거나 같은경우
		return tempCnt;
		
	}

	private static void print() {System.out.println(ans);}

}
