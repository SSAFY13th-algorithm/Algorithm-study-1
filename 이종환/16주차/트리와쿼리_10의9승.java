import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class 트리와쿼리_10의9승 {
	
	// 여기에 부모 기록. 없다면 자신의 /2가 부모.
	static HashMap<Integer,Integer> mapping = new HashMap<>();
	static StringBuilder sb = new StringBuilder();
	static final int CHANGE = 1, SUM = 2;
	static int qCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int qCnt = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < qCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (order == CHANGE) changeRoot(a,b);
			else getSum(a,b);
		}
		
		System.out.print(sb.toString());
	}

	private static void getSum(int a, int b) {
		// 결국 공통으로 처음 만나는 지점이 어디인지 구해야함
		List<Integer> aToRoot = getList(a);
		List<Integer> bToRoot = getList(b);
		
		int common = -1;
		HashSet<Integer> temp = new HashSet<>();
		temp.addAll(aToRoot);
		for (int n: bToRoot) {
			if (temp.contains(n)) {
				common = n;
				break;
			}
		}
		
		long sum = common;
		sum += add(aToRoot, common);
		sum += add(bToRoot, common);
		sb.append(sum).append("\n");
		
	}
	
	private static long add(List<Integer> list, int common) {
		long res = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i)==common) break;
			res += list.get(i);
		}
		return res;
	}

	private static List<Integer> getList(int num) {
		List<Integer> list = new ArrayList<>();
		
		while(num != 0) {
			list.add(num);
			if(mapping.containsKey(num)) num = mapping.get(num);
			else num /= 2;
		}
		
		return list;
	}

	private static void changeRoot(int nParent, int child) {
		mapping.put(child, nParent);
	}
}
