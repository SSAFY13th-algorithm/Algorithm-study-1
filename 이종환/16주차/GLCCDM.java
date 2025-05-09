import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class GLCCDM {
	static HashMap<Integer,Integer> numCnt = new HashMap<>();
	static StringBuilder sb = new StringBuilder();
	static List<Integer> ans = new ArrayList<>();
	static int small,large,size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 결국 최대공약수와 최소 공약수를 배열의 앞뒤에 위치하면 된다!
		small = Integer.parseInt(st.nextToken()); // 얘가 첫번째
		large = Integer.parseInt(st.nextToken()); // 얘가 마지막
		size = Integer.parseInt(st.nextToken());
		
		if (large % small != 0) { // 불가능한 상황
			System.out.println(-1);
			return;
		}
		
		getNums();
		getAns();
		sorting();
		print();
	}
	private static void print() {System.out.println(sb.toString());}

	private static void sorting() {
		if (ans.size() != size) sb.append(-1);
		else {
			Collections.sort(ans,Comparator.naturalOrder());
			for (int i = 0; i < ans.size()-1; i++) {
				sb.append(ans.get(i)).append(" ");
			}
			//최소 공배수를 위해 항상 제일 큰 수를 마지막에 넣어줌
			sb.append(large);
		}
	}

	private static void getAns() {
		ans.add(small);
		for (int n: numCnt.keySet()) {
			int length = ans.size();
			for (int i = 0; i < length; i++) {
				for (int j = 1; j <= numCnt.get(n); j++) {
					// 최소공배수와 최대공약수 사이의 배수들을 추가해줌
					ans.add((int) (ans.get(i)* Math.pow(n, j)));
					if (ans.size() == size) return;
					
				}
			}
		}
	}

	private static void getNums() {
		int target = large/small;
		
		while(target >= 2) {
			boolean isEnd = true;
			for (int i = 2; i*i <= target; i++) {
				if (target%i == 0) {
					target /= i;
					add(i);
					isEnd = false;
					break;
				}
			}
			
			if (isEnd) {
				if(target!= 1) add(target);
				break;
			}
		}
	}

	private static void add(int num) {
		if (numCnt.containsKey(num)) {
			numCnt.replace(num, numCnt.get(num)+1);
		} else numCnt.put(num, 1);
		
	}
}
