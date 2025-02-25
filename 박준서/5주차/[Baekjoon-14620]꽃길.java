import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[] dx = { 0, 0, 1, -1 };
    private static final int[] dy = { -1, 1, 0, 0 };

    private static int N, ans = Integer.MAX_VALUE;
    private static int[][] matrix; // 화단의 비용을 저장할 2차원 배열

    // 씨앗을 심은 위치를 저장할 ArrayList
    private static ArrayList<Point> seed = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        back(1, 1);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    // 백트래킹으로 씨앗을 심을 위치를 선택하는 함수
    public static void back(int y, int x) {
        // 씨앗을 3개 모두 심었다면
        if (seed.size() == 3) {
            ans = Math.min(ans, calc()); // 최소 비용 갱신
            return;
        }

        // 화단을 순회하며 씨앗을 심을 위치 선택
        for (int i = y; i < N - 1; i++) {
            for (int j = (i == y ? x : 1); j < N - 1; j++) {
                boolean isValid = true;
                // 이미 심은 씨앗들과의 거리 확인
                for (Point elem : seed) {
                    // 맨해튼 거리가 2 이하면 꽃잎이 겹침
                    if (Math.abs(i - elem.y) + Math.abs(j - elem.x) <= 2) {
                        isValid = false;
                        break;
                    }
                }
                if (!isValid)
                    continue;

                seed.add(new Point(j, i));
                back(i, j + 1); // 백트래킹
                seed.remove(seed.size() - 1);
            }
        }
    }

    // 현재 심은 씨앗들의 총 비용을 계산하는 함수
    public static int calc() {
        int cost = 0;
        for (Point p : seed) {
            cost += matrix[p.y][p.x]; // 씨앗 위치의 비용
            // 상하좌우 꽃잎의 비용 추가
            for (int k = 0; k < 4; k++) {
                cost += matrix[p.y + dy[k]][p.x + dx[k]];
            }
        }
        return cost;
    }
}
