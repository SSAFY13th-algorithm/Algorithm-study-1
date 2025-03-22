import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 장난감조립 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int goal = Integer.parseInt(br.readLine());
		int relationCnt = Integer.parseInt(br.readLine());
		
		ArrayList<Toy> toys = new ArrayList<>();
		
		for (int i = 0; i<= goal; i++) toys.add(new Toy(i));
		int[] leftParentCnt = new int[goal+1];
		
		for (int i = 1; i <= relationCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int large = Integer.parseInt(st.nextToken());
			int small = Integer.parseInt(st.nextToken());
			int need = Integer.parseInt(st.nextToken());
			
			// 자신이 필요한 부품과 개수 기억
			toys.get(large).parts.put(small, need);
			//자신을 필요로 하는 장난감 기억
			toys.get(small).larger.add(large);
			//부모(필요한 장난감종류)카운트 증가
			leftParentCnt[large]++;
		}
		
		Queue<Toy> q = new LinkedList<>();
		
		// parts의 size가 1 == 자기 자신 뿐
		for (int i = 1; i <= goal; i++)  {
			
			if(leftParentCnt[i] != 0) continue;
			// 기본 부품임을 표기
			leftParentCnt[i] = -1;
			// 초기값은 자기자신 1개
			toys.get(i).parts.put(i,1);
			q.add(toys.get(i));
		}
		
		while(!q.isEmpty()) {
			Toy toy = q.poll();
			
			for (int lNum: toy.larger) {
				Toy larger = toys.get(lNum);
				int toyCnt = larger.parts.get(toy.num);
				
				larger.parts.remove(toy);
				if (--leftParentCnt[larger.num] == 0) q.add(larger);
				
				if (leftParentCnt[toy.num] == -1) continue;
				
				//전부 larger에게 전부
				for (int part: toy.parts.keySet()) {
					//더해줄 부품-> toy에게 필요함 part의 개수 * toyCnt
					
					int addCnt = toy.parts.get(part) * toyCnt;
					
					if (larger.parts.containsKey(part)) larger.parts.replace(part,larger.parts.get(part) + addCnt);
					else larger.parts.put(part, addCnt);
				}
			}
		}
		
		Map<Integer, Integer> goalToyParts = toys.get(goal).parts;
		for (int i = 1; i< goal; i++) {
			if (leftParentCnt[i] == -1) {
				if(goalToyParts.containsKey(i)) sb.append(i).append(" ").append(goalToyParts.get(i)).append("\n");
			}
		}
		
		System.out.println(sb.toString());
		

	}
	
	static class Toy{
		int num;
		//나를 필요로 하는 장난감들.
		Set<Integer> larger = new HashSet<>();
		
		//내가 필요한 부품
		Map<Integer, Integer> parts = new HashMap<>();

		public Toy(int num) {
			this.num = num;
		}
		
		
	}

}
