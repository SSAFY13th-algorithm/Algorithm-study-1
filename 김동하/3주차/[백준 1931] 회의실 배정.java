package one;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringBuilder sb = new StringBuilder();
	public static int n;
	public static int ans;
	public static List<Meeting> list = new ArrayList<>();
	
	public static class Meeting{
		int st,ed;
		public Meeting(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
	}
	public static class MeetingComparator implements Comparator<Meeting> {
		@Override
		public int compare(Meeting o1, Meeting o2) {
			if(o1.ed == o2.ed) return o1.st - o2.st;
			return o1.ed - o2.ed;
		}
	}
	
	public static void init() throws IOException{
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			list.add(new Meeting(in[0], in[1]));
		}
		Collections.sort(list,new MeetingComparator());
	}
	
	public static void setMeet() {
		int curTime = 0,cnt = 0;
		for(int i = 0; i < n; i++) {
			if(curTime <= list.get(i).st) {
				curTime = list.get(i).ed;
				cnt++;
			}
		}
		ans = cnt;
	}

	public static void solution() throws IOException{
		init();
		setMeet();
		sb.append(ans);
		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args)throws IOException {
		solution();
	}

}
