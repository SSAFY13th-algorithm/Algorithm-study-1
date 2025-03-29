import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    private static int N, M; // N: 첫 번째 배열의 크기, M: 두 번째 배열의 크기
    private static TreeSet<Integer> TS; // 첫 번째 배열의 숫자들을 저장할 TreeSet

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();

        TS = new TreeSet<>();

        readLine();
        for (int i = 0; i < N; i++)
            TS.add(nextInt());

        readLine();
        M = nextInt();
        readLine();

        // 두 번째 배열의 각 숫자가 첫 번째 배열에 존재하는지 확인
        for (int i = 0; i < M; i++) {
            if (TS.contains(nextInt())) // TreeSet에 해당 숫자가 있으면
                bw.write("1 "); // 1 출력
            else // TreeSet에 해당 숫자가 없으면
                bw.write("0 "); // 0 출력
        }
        bw.write("\n");

        printAnswer();
    }
}
