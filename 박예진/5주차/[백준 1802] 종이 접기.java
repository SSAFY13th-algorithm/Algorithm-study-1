import java.util.*;
import java.io.*;

/*
 * 1은 out, 0은 in
 * 중간값을 기준으로 대칭되는 좌우 값 달라야함
 * */

class Main {
	static int T;
	static String str;
	
    public static void main(String args[]) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	T = Integer.parseInt(st.nextToken());
    	while(T-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		str = st.nextToken();
    		
    		boolean flag = check(0, str.length() - 1);
    		System.out.println(flag ? "YES" : "NO");
    	}
    }
    
    static boolean check(int left, int right) {
    	if (left == right) return true;
    	
    	int mid = (left + right) / 2;
    	for(int i = left; i < mid; i++) {
    		if (str.charAt(i) == str.charAt(right - i)) return false;
    	}
    	return check(left, mid - 1) && check(mid + 1, right);
    }
}
