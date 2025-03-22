import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class 회전초밥 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int plateCnt = Integer.parseInt(st.nextToken());
		int sushiCnt = Integer.parseInt(st.nextToken());
		int serialPlate = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken());
		int max =1;
		
		Map<Integer,Integer> map = new HashMap<>();
		map.put(coupon, 1);
		
		int[] arr = new int[plateCnt];
		for (int i = 0; i < plateCnt; i++) arr[i] = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <  serialPlate; i++) {
			int target = arr[i];
			
			if (!map.containsKey(target)) map.put(target, 1);
			else map.replace(target, map.get(target)+1);
			
		}
		max =Math.max(max, map.size());
		
		for (int i = 0; i < plateCnt; i++) {
			
			
			int removeT = arr[i];
			int newT = arr[(i+serialPlate)%plateCnt];
			
			if (map.get(removeT) == 1) map.remove(removeT);
			else map.replace(removeT, map.get(removeT)-1);
			
			if (!map.containsKey(newT)) map.put(newT, 1);
			else map.replace(newT, map.get(newT)+1);
			
			max =Math.max(max, map.size());
		}
		
		System.out.println(max);
		
	}
}
