import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
    private static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };

    private static int N, M, K, ans; // N: 격자 크기, M: 파이어볼 개수, K: 이동 횟수, ans: 최종 질량 합
    private static ArrayList<fireBall> magics = new ArrayList<>(); // 파이어볼 리스트

    // 파이어볼 클래스 정의
    static class fireBall {
        int row; // 행 위치
        int colunm; // 열 위치
        int mass; // 질량
        int speed; // 속력
        int direction; // 방향

        public fireBall(int row, int colunm, int mass, int speed, int direction) {
            this.row = row;
            this.colunm = colunm;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();
        M = nextInt();
        K = nextInt();

        for (int i = 0; i < M; i++) {
            readLine();
            int r = nextInt();
            int c = nextInt();
            int m = nextInt();
            int s = nextInt();
            int d = nextInt();
            magics.add(new fireBall(r - 1, c - 1, m, s, d));
        }

        // K번 이동 시뮬레이션 수행
        while (K-- != 0) {
            magics = move(); // 파이어볼 이동 및 합치기/나누기 처리
        }

        for (fireBall fb : magics)
            ans += fb.mass;
        sb.append(ans).append("\n");

        printAnswer();
    }

    // 파이어볼 이동 및 합치기/나누기 처리 메소드
    public static ArrayList<fireBall> move() {
        // 각 위치별로 파이어볼을 저장할 2차원 리스트를 1차원으로 표현
        ArrayList<ArrayList<fireBall>> nextMagics = new ArrayList<>(N * N);
        for (int i = 0; i < N * N; i++)
            nextMagics.add(new ArrayList<>());

        // 모든 파이어볼 이동
        for (fireBall fb : magics) {
            // 다음 위치 계산 (속력만큼 이동, 격자 범위를 벗어나면 반대편으로 이어짐)
            int nextLocationX = (fb.colunm + dx[fb.direction] * (fb.speed % N) + N) % N;
            int nextLocationY = (fb.row + dy[fb.direction] * (fb.speed % N) + N) % N;
            int nextIdx = nextLocationY * N + nextLocationX; // 2차원 좌표를 1차원 인덱스로 변환

            // 새 파이어볼 객체 생성하여 다음 위치에 추가
            fireBall nextFB = new fireBall(nextLocationY, nextLocationX, fb.mass, fb.speed, fb.direction);
            nextMagics.get(nextIdx).add(nextFB);
        }

        // 이동 후 같은 칸에 있는 파이어볼 처리
        ArrayList<fireBall> returnFB = new ArrayList<>();
        for (int i = 0; i < N * N; i++) {
            if (nextMagics.get(i) == null || nextMagics.get(i).size() == 0)
                continue; // 파이어볼이 없는 칸은 건너뜀

            ArrayList<fireBall> nowFBs = nextMagics.get(i);
            if (nowFBs.size() == 1) {
                // 한 칸에 파이어볼이 하나만 있으면 그대로 추가
                returnFB.add(nowFBs.get(0));
            } else {
                // 여러 개 있으면 합치고 나누는 작업 수행
                ArrayList<fireBall> dividedFBs = divide(nowFBs);
                if (dividedFBs == null)
                    continue; // 질량이 0이면 소멸

                // 나눠진 파이어볼들 추가
                for (fireBall nowFB : dividedFBs) {
                    returnFB.add(nowFB);
                }
            }
        }

        return returnFB; // 이동 후 처리된 파이어볼 리스트 반환
    }

    // 같은 칸에 있는 파이어볼들을 합치고 나누는 메소드
    public static ArrayList<fireBall> divide(ArrayList<fireBall> nowFBs) {
        ArrayList<fireBall> returnFBs = new ArrayList<>();
        boolean directionFlag = true; // 모든 파이어볼의 방향이 모두 홀수이거나 모두 짝수인지 확인하는 플래그

        // 합쳐질 파이어볼들의 위치는 동일
        int nextRow = nowFBs.get(0).row;
        int nextCol = nowFBs.get(0).colunm;
        int nextMass = 0, nextSpeed = 0, fbSize = nowFBs.size();

        // 방향의 홀짝 여부 확인
        for (int i = 1; i < fbSize; i++)
            if ((nowFBs.get(i - 1).direction) % 2 != (nowFBs.get(i).direction) % 2)
                directionFlag = false;

        // 질량과 속력 합산
        for (fireBall fb : nowFBs) {
            nextMass += fb.mass;
            nextSpeed += fb.speed;
        }

        // 새 파이어볼 특성 계산
        nextMass /= 5; // 질량은 합/5
        nextSpeed /= fbSize; // 속력은 합/개수

        if (nextMass == 0)
            return null; // 질량이 0이면 소멸

        // 방향 결정 및 4개의 파이어볼 생성
        int i = 0;
        if (!directionFlag)
            i++; // 방향이 일치하지 않으면 1,3,5,7 방향으로
        for (; i < 8; i += 2) { // 방향이 일치하면 0,2,4,6 방향으로
            returnFBs.add(new fireBall(nextRow, nextCol, nextMass, nextSpeed, i));
        }

        return returnFBs; // 나눠진 파이어볼 리스트 반환
    }
}
