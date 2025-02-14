import java.util.*;

public class Main {
    static int N;
    static int[] population;
    static List<Integer>[] graph;
    static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        population = new int[N + 1];
        graph = new ArrayList[N + 1];

        // 인구 입력
        for (int i = 1; i <= N; i++) {
            population[i] = sc.nextInt();
        }

        // 그래프 입력
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            int count = sc.nextInt();
            for (int j = 0; j < count; j++) {
                int neighbor = sc.nextInt();
                graph[i].add(neighbor);
            }
        }

        // 부분집합 탐색
        for (int i = 1; i < (1 << N) - 1; i++) {
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0) groupA.add(j + 1);
                else groupB.add(j + 1);
            }

            if (isConnected(groupA) && isConnected(groupB)) {
                int sumA = calculatePopulation(groupA);
                int sumB = calculatePopulation(groupB);
                minDifference = Math.min(minDifference, Math.abs(sumA - sumB));
            }
        }

        System.out.println(minDifference == Integer.MAX_VALUE ? -1 : minDifference);
    }

    // 연결성 확인 (BFS)
    static boolean isConnected(List<Integer> group) {
        if (group.isEmpty()) return false;
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(group.get(0));
        visited[group.get(0)] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph[current]) {
                if (group.contains(neighbor) && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }

        return count == group.size();
    }

    // 인구 합산
    static int calculatePopulation(List<Integer> group) {
        int sum = 0;
        for (int g : group) {
            sum += population[g];
        }
        return sum;
    }
}
