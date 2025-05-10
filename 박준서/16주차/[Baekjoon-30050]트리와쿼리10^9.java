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

    private static int N; // 쿼리의 개수
    private static Map<Integer, Integer> map; // 노드의 부모 정보를 저장하는 맵 (key: 자식 노드, value: 부모 노드)

    public static void main(String args[]) throws Exception {
        init();

        // N개의 쿼리 처리
        while (N-- != 0) {
            int oper = nextInt(); // 연산 종류 (1: 부모 설정, 2: LCA 찾기)
            int a = nextInt(); // 첫 번째 노드
            int b = nextInt(); // 두 번째 노드

            if (oper == 1) {
                // 부모 설정 연산: b의 부모를 a로 설정
                map.put(b, a);
            } else {
                // LCA 찾기 연산: a와 b의 LCA를 찾아 출력
                bw.write(findLCA(a, b) + "\n");
            }
        }

        bwEnd();
    }

    // 초기화
    public static void init() throws Exception {
        N = nextInt(); // 쿼리 개수 입력
        map = new HashMap<>(); // 부모 정보 맵 초기화
    }

    // 두 노드의 최소 공통 조상(LCA)을 찾고, 경로 상의 모든 노드 값의 합을 반환하는 메소드
    public static long findLCA(int a, int b) {
        int aLvl = findLvl(a); // a의 레벨(깊이) 찾기
        int bLvl = findLvl(b); // b의 레벨(깊이) 찾기
        long ret = 0; // 경로 상의 노드 값 합을 저장할 변수

        // a의 레벨이 b의 레벨보다 크면 두 노드를 교환
        // 항상 b의 레벨이 같거나 더 깊도록 설정
        if (aLvl > bLvl) {
            int tmp = aLvl;
            aLvl = bLvl;
            bLvl = tmp;

            tmp = a;
            a = b;
            b = tmp;
        }

        // b를 a와 같은 레벨로 올리면서 경로 상의 노드 값을 더함
        while (aLvl < bLvl) {
            ret += b; // 현재 b 노드 값을 더함
            bLvl--; // b의 레벨을 1 감소

            // b의 부모 노드로 이동
            if (map.containsKey(b))
                b = map.get(b); // 맵에 저장된 부모가 있으면 그 값을 사용
            else
                b /= 2; // 기본 이진 트리 규칙 적용 (부모 = 자식/2)
        }

        // a와 b가 같은 레벨에서 시작하여 LCA를 찾을 때까지 위로 이동
        while (a != b) {
            // b의 부모로 이동하면서 b 값을 더함
            ret += b;
            if (map.containsKey(b))
                b = map.get(b);
            else
                b /= 2;

            // a의 부모로 이동하면서 a 값을 더함
            ret += a;
            if (map.containsKey(a))
                a = map.get(a);
            else
                a /= 2;
        }

        // LCA 노드 값도 더해서 반환
        return ret + a;
    }

    // 노드의 레벨(루트로부터의 깊이)을 찾는 메소드
    public static int findLvl(int a) {
        if (a == 1) // 루트 노드인 경우 레벨 1 반환
            return 1;

        // 맵에 저장된 부모가 있으면 그 부모의 레벨 + 1 반환
        if (map.containsKey(a))
            return findLvl(map.get(a)) + 1;

        // 기본 이진 트리 규칙 적용 (부모 = 자식/2)의 레벨 + 1 반환
        return findLvl(a / 2) + 1;
    }
}
