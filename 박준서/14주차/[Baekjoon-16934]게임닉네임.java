import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st = new StringTokenizer("");

    private static void readLine() throws Exception {
        st = new StringTokenizer(br.readLine());
    }

    static String nextToken() throws Exception {
        while (!st.hasMoreTokens())
            readLine();
        return st.nextToken();
    }

    private static int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    private static void bwEnd() throws Exception {
        bw.flush();
        bw.close();
    }

    private static int N; // 입력 문자열 개수
    private static Node Trie; // Trie(접두사 트리)의 루트 노드

    private static class Node {
        int isEnd; // 이 노드에서 끝나는 문자열의 개수
        Map<Character, Node> ch; // 자식 노드 (문자 -> 노드)

        public Node(int isEnd) {
            this.isEnd = isEnd;
            this.ch = new TreeMap<>();
        }
    }

    public static void main(String args[]) throws Exception {
        N = nextInt(); // 문자열 개수 입력
        Trie = new Node(0); // Trie 루트 노드 생성

        while (N-- != 0) {
            char[] in = nextToken().toCharArray(); // 입력 문자열을 문자 배열로 변환

            Node now = Trie; // 현재 노드를 루트로 시작
            boolean flag = false; // 새로운 노드가 생성되었는지 여부

            // 입력 문자열의 각 문자에 대해 Trie를 탐색/삽입
            for (int i = 0; i < in.length; i++) {
                // 현재 문자로 자식이 없으면 새로운 노드 생성
                if (!now.ch.containsKey(in[i])) {
                    if (!flag) {
                        flag = true; // 처음으로 새로운 노드가 생성되는 지점
                        printCharArr(in, i); // 지금까지의 부분 문자열 출력
                        bw.write("\n");
                    }
                    now.ch.put(in[i], new Node(0)); // 새로운 노드 추가
                }
                now = now.ch.get(in[i]); // 다음 노드로 이동
            }
            now.isEnd++; // 문자열이 끝나는 노드의 isEnd 증가

            // 만약 기존에 없던 문자열이 아니라면(즉, flag가 false라면)
            if (!flag) {
                printCharArr(in, in.length - 1); // 전체 문자열 출력
                if (now.isEnd != 1) // 이미 존재하는 문자열이면 등장 횟수도 출력
                    bw.write(now.isEnd + "");
                bw.write("\n");
            }
        }

        bwEnd();
    }

    // 문자 배열의 0~e까지를 출력하는 함수
    private static void printCharArr(char[] in, int e) throws IOException {
        for (int i = 0; i <= e; i++)
            bw.write(in[i]);
    }
}
