import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int a, b, c;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calc(a, b, c));
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    //분할정복을 통해서 거듭제곱 계산
    public static long calc(long a, long b, long c) {
        //곱해햐 할 횟수가 1이면 바로 반환
        if (b == 1) {
            return a % c;
        }
        //지수를 반으로
        long half = calc(a, b / 2, c);
        half = (half * half) % c;

        //지수가 짝수일 경우 반환하고 홀수면 a를 한번 더 곱하기
        if (b % 2 == 0) {
            return half;
        } else {
            return (half * a) % c;
        }
    }
}