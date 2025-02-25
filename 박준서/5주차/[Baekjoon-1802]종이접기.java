import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            String in = br.readLine();
            if (divide(in))
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // 문자열이 좋은 문자열인지 검사하는 재귀 함수
    public static boolean divide(String str) {
        // 문자열의 중간 지점 계산
        int mid = str.length() / 2;

        // 기저 조건: 문자열 길이가 1이면 true 반환
        if (mid == 0)
            return true;

        // 재귀적으로 왼쪽 부분과 오른쪽 부분이 좋은 문자열인지 확인
        if (divide(str.substring(0, mid)) && divide(str.substring(mid + 1))) {
            // 양쪽 끝에서부터 중앙으로 가면서 대칭되는 문자들 비교
            for (int i = 0; i < mid; i++) {
                // 대칭되는 위치의 문자가 같으면 좋은 문자열이 아님
                if (str.charAt(i) == str.charAt(str.length() - i - 1))
                    return false;
            }
            // 모든 대칭 위치의 문자가 다르면 좋은 문자열
            return true;
        }
        // 왼쪽이나 오른쪽 부분 중 하나라도 좋은 문자열이 아니면 false
        return false;
    }
}
