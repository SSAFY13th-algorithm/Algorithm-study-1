import java.util.*;
import java.io.*;

public class Main {
	
	static String str;
	static Set<String> s;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		str = br.readLine();
		
		// 해결
		s = new HashSet<>();
		solution();
		
		// 출력
		System.out.println(s.size());		
	}
	
	// 문자열 중복제거
	public static void solution() {
		for(int i = 0; i < str.length(); i++) {
			StringBuffer res = new StringBuffer();
			for(int j = i; j < str.length(); j++) {
				res.append(str.charAt(j));
				s.add(res.toString());
			}
		}
	}
}
