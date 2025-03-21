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

    private static int N, M;
    private static Trie trie;

    static class Trie {
        // 현재 노드가 단어의 끝인지 표시하는 플래그
        boolean isEnd;
        // 다음 문자에 대한 트라이 노드를 저장하는 해시맵
        HashMap<Character, Trie> next;

        public Trie() {
            this.isEnd = false;
            this.next = new HashMap<>();
        }
    }

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();

        while (N-- != 0) {
            // 일관성 여부를 나타내는 플래그 (true: 일관성 있음, false: 일관성 없음)
            boolean flag = true;
            readLine();
            M = nextInt();

            trie = new Trie();

            // 각 전화번호 처리
            while (M-- != 0) {
                // 전화번호를 문자 배열로 입력받음
                char[] line = br.readLine().toCharArray();
                // 이미 일관성이 없다고 판단된 경우 입력만 받고 처리는 건너뜀
                if (!flag)
                    continue;

                // 트라이의 루트 노드부터 시작
                Trie now = trie;

                // 전화번호의 각 자리 숫자를 트라이에 삽입
                for (int i = 0; i <= line.length; i++) {
                    // 전화번호의 끝에 도달한 경우
                    if (i == line.length) {
                        // 현재 노드를 단어의 끝으로 표시
                        now.isEnd = true;
                        continue;
                    }

                    // 현재 노드가 다른 전화번호의 끝이라면 일관성이 없음
                    // (현재 번호가 다른 번호의 prefix인 경우)
                    if (now.isEnd) {
                        flag = false;
                        break;
                    }

                    // 현재 문자에 해당하는 노드가 이미 존재하는 경우
                    if (now.next.containsKey(line[i])) {
                        // 마지막 문자이고 이미 해당 경로가 존재한다면 일관성이 없음
                        // (다른 번호가 현재 번호의 prefix인 경우)
                        if (i == line.length - 1) {
                            flag = false;
                            break;
                        }
                        now = now.next.get(line[i]);
                    } else {
                        now.next.put(line[i], new Trie());
                        now = now.next.get(line[i]);
                    }
                }
            }

            if (flag)
                bw.write("YES\n");
            else
                bw.write("NO\n");
        }
        printAnswer();
    }
}
