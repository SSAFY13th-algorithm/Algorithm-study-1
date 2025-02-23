import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb;
	public static int ans;
	public static int tc;
	public static String str;
	public static Map<String,Boolean> m = new HashMap<>();
	public static void init() throws IOException {
		str = br.readLine();
	}

	public static void solution() throws IOException{
		init();
		int n = str.length();
		for(int i = 0; i < n; i++) {
			for(int j = i; j <= n; j++) {
				m.put(str.substring(i,j), true);
			}
		}
		System.out.println(m.size() - 1);
	}
	public static void main(String[] args) throws IOException {
		solution();
	}
}
