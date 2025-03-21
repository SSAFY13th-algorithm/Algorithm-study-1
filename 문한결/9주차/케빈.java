import java.util.*;
import java.io.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int answer;
    static int kevinNum = Integer.MAX_VALUE;
    static ArrayList<ArrayList<int[]>> list;
    static int[] costs;
    static int input[];



    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        System.out.println(answer);



    }

    static void init() throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        list = new ArrayList<>();
        for (int i = 0; i < n + 1; i++)
            list.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.get(input[0]).add(new int[] {input[1], 1});
            list.get(input[1]).add(new int[] {input[0], 1});
        }

    }

    static void findAnswer() {
        for (int i = 1; i <= n; i++) {
            Arrays.fill(costs, Integer.MAX_VALUE);
            dijkstra(i);
        }

    }

    static void dijkstra(int start) {
        Queue<int[]> que = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]);
        que.add(new int[] {0, start});
        costs[start] = 0;
        while (!que.isEmpty()) {
            int cn[] = que.remove();
            int curCost = cn[0], curNode = cn[1];
            if (costs[curNode] < curCost)
                continue;
            for (int nc[] : list.get(curNode)) {
                if (costs[nc[0]] > curCost + nc[1]) {
                    costs[nc[0]] = curCost + nc[1];
                    que.add(new int[] {costs[nc[0]], nc[0]});
                }
            }
        }
        if (kevinNum > Arrays.stream(costs).sum()) {
            answer = start;
            kevinNum = Arrays.stream(costs).sum();
        }


    }


}
