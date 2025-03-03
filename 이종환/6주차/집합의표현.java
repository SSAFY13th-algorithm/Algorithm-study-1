import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합의표현 {
	static int arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		int operationCnt = Integer.parseInt(st.nextToken());
		
		
		//그 숫자가 포함된 최상위 그룹
		arr = new int[cnt+1];
		for (int i = 0; i <= cnt; i++) {
			arr[i] = i; // 
		}
		
		
		for (int i =0; i < operationCnt; i++) {
			st = new StringTokenizer(br.readLine());
			
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (a > b) {
				int temp = b;
				b = a;
				a = temp;
			}
			
			if (order == 0) {
			    int rootA = find(a);
			    int rootB = find(b);
			    if (rootA != rootB) {
			        arr[rootB] = rootA;  // 두 루트를 합침
			    }
			} else {
				if (find(b) == find(a)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
		
		
	}
	
	private static int find(int x) {
		//루트 압축
		if (arr[x] != x) {
			arr[x] = find(arr[x]);
		} 
		return arr[x];
	}
}
