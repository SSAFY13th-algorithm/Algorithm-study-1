import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;
    public static int[] arr;

    public static class Node{
        int st, ed;
        public Node(int st, int ed) {
            this.st = st;
            this.ed = ed;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node n1, Node n2) {
            if(n1.st == n2.st) return n1.ed - n2.ed;
            return n1.st - n2.st;
        }
    }
    public static List<Node> classes;

    public static void init() throws IOException{
        n = Integer.parseInt(br.readLine());
        classes = new ArrayList<>();
        StringTokenizer stk;
        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int st, ed;
            st = Integer.parseInt(stk.nextToken());
            ed = Integer.parseInt(stk.nextToken());
            classes.add(new Node(st,ed));
        }
        Collections.sort(classes, new NodeComparator());
    }

    public static int greedy() {
        int cnt = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(0);
        for(Node cur : classes) {
            if(pq.peek() <= cur.st) pq.remove();
            else cnt++;
            pq.add(cur.ed);
        }
        return cnt;
    }
    public static void solution() throws IOException{
        init();
        sb.append(greedy());
    }

    public static void main(String[] args) throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
