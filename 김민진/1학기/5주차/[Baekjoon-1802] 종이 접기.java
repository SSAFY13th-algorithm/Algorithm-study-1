package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5PaperFolding {
	
	private static String	input;

	public static void main(String[] args) throws IOException {
		sol();
	}
	
	private static void sol() throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		
		int	T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			input = br.readLine();
			
			System.out.println(canFold(0, input.length() - 1) ? "YES" : "NO");
		}
	}
	
	private static boolean canFold(int s, int e) {
		if (s == e) return true;
		
		if (!checkValid(s, e)) return false;
		
		int	mid = (e + s) / 2;
		
		return canFold(s, mid-1) && canFold(mid+1, e);
	}
	
	private static boolean checkValid(int s, int e) {
		while (s != e) {
			if (input.charAt(s++) == input.charAt(e--))
				return false;
		}
		return true;
	}
}
