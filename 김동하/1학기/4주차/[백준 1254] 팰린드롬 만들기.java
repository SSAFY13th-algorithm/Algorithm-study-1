import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb;
	public static int ans;
	public static int tc;
	public static String str;
	public static void init() throws IOException {
		str = br.readLine();
	}
	public static boolean palindrom(String s) {
		int left = 0, right = s.length() - 1;
		while(left <= right) {
			if(s.charAt(left) == s.charAt(right)) {
				left++;
				right--;
			}
			else return false;
		}
		return true;
	}
	
	public static void solution() throws IOException{
		init();
		ans = str.length();
		for(int i = 0; i < str.length(); i++) {
			if(palindrom(str.substring(i))) {
				System.out.println(ans);
				return;
			}
			ans++;
		}
	}
	public static void main(String[] args) throws IOException {
		solution();
	}
}
