import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 행복_유치원 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int peopleCnt = Integer.parseInt(st.nextToken());
		int teamCnt = Integer.parseInt(st.nextToken());
		
		int[] people = new int[peopleCnt];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < peopleCnt; i++) people[i] = Integer.parseInt(st.nextToken());
		
		int[] diff = new int[peopleCnt-1];
		for (int i = 0; i < peopleCnt-1; i++) diff[i] = people[i+1] - people[i];
		
		Arrays.sort(diff);
		
		int ans = people[peopleCnt-1] - people[0];
		for (int i = 0; i < teamCnt-1; i++) ans -= diff[peopleCnt-2-i];
		
		System.out.println(ans);
	}
}
