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

    private static int N;
    private static String str;

    public static void main(String[] args) throws IOException {
        // 입력 처리
        readLine();
        N = nextInt();
        str = br.readLine();

        // 각 알파벳 문자의 등장 횟수를 저장할 배열 (a-z: 26개)
        int[] charactors = new int[26];

        int p1 = 0; // 윈도우의 시작 포인터
        int p2 = 0; // 윈도우의 끝 포인터
        int ans = 0; // 결과값: 최대 길이
        int chars = 0; // 현재 윈도우 내 서로 다른 문자의 개수
        int len = str.length(); // 문자열 길이

        // 슬라이딩 윈도우 알고리즘 시작
        while (p1 < len && p2 < len) {
            // p2 포인터를 전진시키며 문자 추가
            if (charactors[str.charAt(p2++) - 'a']++ == 0)
                chars++; // 새로운 종류의 문자가 추가되면 카운트 증가

            // 서로 다른 문자의 개수가 N을 초과하면 p1 포인터를 전진시켜 윈도우 축소
            while (chars > N) {
                if (charactors[str.charAt(p1++) - 'a']-- == 1)
                    chars--; // 특정 문자가 윈도우에서 완전히 제거되면 카운트 감소
            }

            // 현재 윈도우 길이와 지금까지의 최대 길이 비교하여 갱신
            ans = Math.max(ans, p2 - p1);
        }

        sb.append(ans).append("\n");
        printAnswer();
    }
}
