import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class 전화번호목록 {
	static HashSet<String> numbers;
	static BufferedReader br;
	static PriorityQueue<Number> pq;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int tc =Integer.parseInt(br.readLine());
		for (int test = 1; test<=tc; test++) process();
		
	}
	
	private static void process() throws NumberFormatException, IOException {
		numbers = new HashSet<>();
		int numCnt = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		for (int i = 0; i < numCnt; i++) pq.add(new Number(br.readLine()));
		
		checkConsistency();
	}
	
	
	private static void checkConsistency() {
		while(!pq.isEmpty()) {
			String target = pq.poll().num;

			if (numbers.contains(target)) {
				System.out.println("NO");
				return;
			}
			
			for (int i = 1; i<= target.length(); i++) numbers.add(target.substring(0, i));
			
		}
		System.out.println("YES");
		return;
	}



	static class Number implements Comparable<Number>{
		String num;
		
		public Number(String num) {
			super();
			this.num = num;
		}


		@Override
		public int compareTo(Number o) {
			// TODO Auto-generated method stub
			return o.num.length() - this.num.length();
		}
		
	}
}
