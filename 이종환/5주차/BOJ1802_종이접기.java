import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= tc; test_case++) {
			String[] input = (" " + br.readLine()).split(""); // 인덱스맞추는 편의를 위해 " "추가
			int mid = 1;
			String ans = "YES";
			
			loop:
			while (mid != input.length) {
				for (int i = 1; i < mid; i++) {
					if (input[mid-i].equals(input[mid+i])) {
						ans = "NO";
						break loop;
					}
				}
				mid *= 2;
			}
			
			System.out.println(ans);
		}
	}
}
