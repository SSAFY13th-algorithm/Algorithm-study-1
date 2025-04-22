import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 합_구하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int len = Integer.parseInt(br.readLine());
		int[] arr = new int[len];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		SegmentTree sTree = new SegmentTree(arr);
		
		int rangeCnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < rangeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken())-1;
			sb.append(sTree.query(l, r)).append("\n");
		}

		System.out.println(sb.toString());
	}
	
	static class SegmentTree{
		int n;
		int[] arr;
		int[] tree;
		
		public SegmentTree(int[] input) {
			n = input.length;
			arr = input.clone();
			tree = new int[n*4];
			build(1, 0, n-1);
		}

		private void build(int node, int start, int end) {
			if (start == end) {
				tree[node] = arr[start];
				return;
			}
			
			int mid = (start+end)/2;
			
			build(node*2, start, mid);
			build(node*2+1,mid+1,end);
			
			tree[node] = tree[node*2] +tree[node*2+1]; 
			
		}
	    public int query(int l, int r) {
	        return query(1, 0, n - 1, l, r);
	    }

	    private int query(int node, int start, int end, int l, int r) {
	        if (r < start || end < l) return 0;             // 겹치지 않음
	        if (l <= start && end <= r) return tree[node];  // 완전히 포함됨
	        int mid = (start + end) / 2;
	        int leftSum = query(node * 2, start, mid, l, r);
	        int rightSum = query(node * 2 + 1, mid + 1, end, l, r);
	        return leftSum + rightSum;
	    }
	    

	}
	
                                                                                                                                                                                                                              
	
}
