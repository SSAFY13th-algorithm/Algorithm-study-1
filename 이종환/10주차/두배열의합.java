import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 두배열의합 {
	static int goal,aSize,bSize;
	static int[] A,B,sumA,sumB;
	static ArrayList<Integer>sectionA = new ArrayList<>();
	static ArrayList<Integer>sectionB = new ArrayList<>();
	static long ans =0;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static HashMap<Integer, Integer> mapA = new HashMap<>();
	static HashMap<Integer, Integer> mapB = new HashMap<>();
	public static void main(String[] args) throws IOException {
		getInput();
		process();
		print();
	}
	
	private static void print() {System.out.println(ans);}

	private static void process() {
		sectionB.sort(Comparator.naturalOrder());
		for (int i = 0; i < sectionA.size(); i++) {
			ans += binarySearch(sectionA.get(i));
		}
	}
	
	private static long binarySearch(int aValue) {
		int target = goal-aValue;
		
		int start = 0;
		int end = sectionB.size();
		int mid = (start+end)/2;
		while(start < end) {
			if (sectionB.get(mid) == target) {
				return (long)mapA.get(aValue) * (long) mapB.get(sectionB.get(mid)) ;
			} else if (sectionB.get(mid) > target) {
				end = mid;
			} else {
				start = mid+1;
			}
			mid = (start+end)/2;
		}
		
		return 0;
		
		
	}

	private static void getInput() throws NumberFormatException, IOException {
		goal = Integer.parseInt(br.readLine());
		aSize = Integer.parseInt(br.readLine());
		A = new int[aSize];
		sumA = new int[aSize];
		calculateSection(A, sumA, sectionA,mapA);
		
		bSize = Integer.parseInt(br.readLine());
		B = new int[bSize];
		sumB = new int[bSize];

		calculateSection(B,sumB,sectionB,mapB);
		
		
	}
	
	private static void calculateSection(int[] t,int[] sum, ArrayList<Integer> section, HashMap<Integer, Integer> map) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		//이렇게 하는게 아니라 만약 합이 같다면 그건 하나만 리스트에 넣고 맵을 통해 몇개 있는지 파악하는 형식으로 해야함.
		for (int i = 0; i < t.length; i++) {
			t[i] = Integer.parseInt(st.nextToken());
			sum[i] = (i == 0)?t[i]:sum[i-1]+t[i];
		}
		
		int len = sum.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j <= i; j++) {
				int sectionSum = (j==0)?sum[i]:sum[i] - sum[j-1];
				if (map.containsKey(sectionSum)) {
					map.replace(sectionSum, map.get(sectionSum) +1);
				} else {
					map.put(sectionSum, 1);
					section.add(sectionSum);
				}
			}
		}
	}


}
