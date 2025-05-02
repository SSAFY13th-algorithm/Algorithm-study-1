import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나머지_합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		
		int[] arr =new int[size];
		int[] curSum = new int[size];
		long[] remainder = new long[goal];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size; i++) {
			//일단 나머지만 정리 -> 이래야 안터짐
			arr[i] = (Integer.parseInt(st.nextToken()))%goal;
			curSum[i] = ((i>0)?(curSum[i-1]+arr[i]):arr[i]);
			// 나머지 별로 정리
			remainder[curSum[i]%goal]++;
		}
		
		long ans = 0;
		for (int i = 0; i < goal; i++) {
			long cnt = remainder[i];
			//나머지가 같은 두가지를 선택해야함
			ans += cnt*(cnt-1)/2;
		}
		ans += remainder[0];
		
		System.out.println(ans);
		
	}
}
