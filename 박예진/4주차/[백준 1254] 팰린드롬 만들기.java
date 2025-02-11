import java.util.*;
import java.io.*;

/*
 * 문자열 원래 길이 + 팰린나온 시점
 * */

public class Main {
	
	static String str;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력
		str = br.readLine();
		
		// 해결
		for(int i = 0; i < str.length(); i++) {
			if (palindrome(i)) {
				answer = str.length() + i;
                System.out.println(answer);
				return;
			}
		}
	}
	
	// 팰린드롬 체크
	public static boolean palindrome(int idx) {
		int start = idx;
		int end = str.length() - 1;
		
		while(start <= end) {
			if (str.charAt(start) != str.charAt(end)) return false;
			start++;
			end--;
		}
		return true;
	}

  /* StringBuffer 사용하여 String reverse 팰린드롬 체크하는 방식
  public static boolean palindrome(int idx) {
		String newStr = str.substring(idx);
		
		StringBuffer sb = new StringBuffer(newStr);
		String reverseStr = sb.reverse().toString();
		
		if (newStr.equals(reverseStr)) return true;
		else return false;
	}*/
}
