import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static String	input;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));

		input = br.readLine().trim();

		int	ans = input.length();
		for (int i = 0; i < input.length()-1; i++) {
			if (isPalindrome(input.substring(i)))
				break;

			ans++;
		}
		System.out.println(ans);
	}

	private static boolean isPalindrome(String str) {
		int	s = 0;
		int e = str.length() - 1;

		while (s < e) {
			if (str.charAt(s++) != str.charAt(e--))
				return false;
		}
		return true;
	}
}
