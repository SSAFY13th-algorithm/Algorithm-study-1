import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static int N, M;
    private static Map<Integer, List<int[]>> graph = new HashMap<>();
    private static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        weights = new int[N + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, cost});
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, cost});
        }

        dijkstra();
        System.out.println(weights[N]);
    }

    private static void dijkstra() {
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        heap.add(new int[]{1, 0});
        weights[1] = 0;

        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int curNode = cur[0];
            int curWeight = cur[1];

            if (curWeight > weights[curNode]) continue;

            for (int[] adj : graph.getOrDefault(curNode, Collections.emptyList())) {
                int neighbor = adj[0];
                int weight = adj[1];
                if (weights[neighbor] > curWeight + weight) {
                    weights[neighbor] = curWeight + weight;
                    heap.add(new int[]{neighbor, weights[neighbor]});
                }
            }
        }
    }
}
