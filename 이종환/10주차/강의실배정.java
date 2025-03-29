import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실배정 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int classCnt = Integer.parseInt(br.readLine());
		
		ArrayList<Time> times = new ArrayList<>();
		for (int i = 0; i < classCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			times.add(new Time(start, end));
		}
		Collections.sort(times);
		PriorityQueue<Time> room = new PriorityQueue<>(new Comparator<Time>() {

			@Override
			public int compare(Time o1, Time o2) {
				return o1.end - o2.end;
			}
			
		});
		
		for (int i = 0; i < times.size(); i++) {
			Time time = times.get(i);
			
			if(room.isEmpty()) {
				room.add(time);
				continue;
			}
			
			// q.peek().end -> 지금 만들어진 강의실에서
			// 현재까지의 사용이 끝난 시각 중 제일 빠른것
			// 이 정보만 계속해서 가져간다
			if(room.peek().end <= time.start){
				
				room.poll();
			}
			
			// 만약 위 조건을 만족하지 못했다면
			// i번째 Use가 어떠한 강의실에서도 수업을 못듣는것임
			// -> 강의실 추가
			room.add(time);
		}
		System.out.println(room.size());
	}
	
	static class Time implements Comparable<Time>{
		int start;
		int end;
		public Time(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Time o) {
			return this.start - o.start;
		}
		
		
	}
}
