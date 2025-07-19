package week3;

import java.util.*;
import java.io.*;

public class Q4AssigningConferenceRoom {
	
	private static int	N;
	
	private static List<int[]>	time;
	
	public static void main(String[] args) throws IOException {
		init();
		sol();
	}
	
	private static void init() throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		time = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[]	input = br.readLine().split(" ");
			
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			
			time.add(new int[] {start, end});
		}
		
		time.sort((x, y) -> {
			if (x[1] == y[1]) return x[0] - y[0];
			
			return x[1] - y[1];
		});
	}
	
	private static void sol() {
		int	cnt = 1;
		int	end = time.get(0)[1];
		
		for (int i = 1; i < N; i++) {
			int	nextStart = time.get(i)[0];
			int nextEnd = time.get(i)[1];
			
			if (nextStart >= end) {
				end = nextEnd;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}