import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 전화돌리기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Node> q = new LinkedList<>();
	static StringTokenizer st;
	static int nodeCnt,edgeCnt,ans = 0;;
	static Node[] nodes;
	
	static class Node{
		int num;
		int childCnt = 0;
		Set<Integer> parent = new HashSet<>();
		
		public Node(int num) {
			super();
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}
	private static void print() {System.out.println(ans);}
	
	private static void process() {
		// 흔히 부모부터 하는 위상정렬과 달리, 여기서는 child 노드가 없는 녀석부터 접근. 
		// 이래야 자식에서 순환이 생기는 노드를 제외할 수 있다.
		// 이때 child 노드의 개수만 기억하면 된다.
		for (int i = 1; i <= nodeCnt; i++) {
			if (nodes[i].childCnt == 0) q.add(nodes[i]);
		}
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			ans++;
			
			for (int parent: n.parent) {
				nodes[parent].childCnt--;
				if (nodes[parent].childCnt == 0) q.add(nodes[parent]);
			}
		}
		
	}
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());
		
		nodes = new Node[nodeCnt+1];
		for (int i = 0; i <= nodeCnt; i++) nodes[i] = new Node(i);
		
		for (int i = 0; i < edgeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if (nodes[to].parent.contains(from)) continue;
			
			nodes[from].childCnt++;
			nodes[to].parent.add(from);
		}
	}
}
