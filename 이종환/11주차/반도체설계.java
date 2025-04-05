import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 반도체설계 {
	static ArrayList<Integer> lis = new ArrayList<>();
	static int size;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}

	private static void print() {
		System.out.println(lis.size());
	}

	private static void process() {
		for (int i = 1; i < size; i++) {
			if (lis.get(lis.size()-1) < arr[i]) {
				lis.add(arr[i]);
			} else {
				lowerBound(arr[i]);
			}
		}
	}

	private static void lowerBound(int target) {
		int st = 0;
		int end = lis.size();
		while(st < end ) {
			int mid = (st+end)/2;
			if (lis.get(mid) < target) {
				st = mid+1;
			} else {
				end = mid;
			}
		}
		
		lis.set(end, target);
	}
	
	private static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[size];
		
		for (int i = 0; i < size; i++) arr[i] = Integer.parseInt(st.nextToken());
		lis.add(arr[0]);
	}


}
