import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class 고냥이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int alphabetMaxCnt = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split("");
		
		int st = 0;
		int end = 0;
		int len = input.length;
		int max = 1;
		int curCnt = 0;
		
		Map<String,Integer> alphabets = new HashMap<String,Integer>();
		
		while (true) {
			String target = input[end];
			
			if (alphabets.containsKey(target)) {
				// 가지고 있는거면 카운트 증가
				alphabets.replace(target, alphabets.get(target)+1);
			} else {
				//아니면 새롭게 추가
				alphabets.put(target, 1);
			}
			
			
			while (alphabets.size() > alphabetMaxCnt) {
				target = input[st];
				alphabets.replace(target,alphabets.get(target)-1);
				
				// 그렇게 개수가 0이되면 맵에서 삭제 -> 알아서 while문 탈출
				if (alphabets.get(target) == 0) alphabets.remove(target);
				st++;
			}
			
			max =Math.max(max, end-st+1);
			
			if (++end == len) break; // 일단 한칸 end 증가
			
		}
		
		System.out.println(max);
	}
}
