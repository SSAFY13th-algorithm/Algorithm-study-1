import java.util.*;
import java.io.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int input[];
    static int answer[];
    static int inDegree[];
    static int[][] cost;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static Queue<Integer> que;
    static int inDegreeForBasic[];
    static List<Integer> basicList;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        printAnswer();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        answer = new int[N + 1];
        inDegree = new int[N + 1];
        cost = new int[N + 1][N + 1];
        inDegreeForBasic = new int[N + 1];
        basicList = new ArrayList<>();
        que = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList());
        }
        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            inDegree[input[1]]++;
            inDegreeForBasic[input[0]]++;
            cost[input[0]][input[1]] = input[2];
            list.get(input[0]).add(input[1]);
        }
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                que.add(i);
            }
        }
    }

    static void findAnswer() {
        while (!que.isEmpty()) {
            int ver = que.remove();
            for (int i : list.get(ver)) {
                answer[i] = answer[i] + (answer[ver] == 0 ? 1 : answer[ver]) * cost[ver][i];
                inDegree[i]--;
                if (inDegree[i] == 0)
                    que.add(i);
            }
        }
        for (int i = 1; i < N + 1; i++) {
            if (inDegreeForBasic[i] == 0)
                basicList.add(i);
        }

    }
    private static void printAnswer() {
        for (int i : basicList) {
            System.out.println(i + " " + answer[i]);
        }
    }



}
