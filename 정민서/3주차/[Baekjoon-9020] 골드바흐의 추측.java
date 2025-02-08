import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        init();
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //테스트 케이스 수
        int test = Integer.parseInt(br.readLine());

        for (int t = 0; t < test; t++) {

            //주어지는 수
            int a = Integer.parseInt(br.readLine());

            for (int i = a / 2; i >= 2; i--) {
                if (isPrime(i) && isPrime(a - i)) {
                    System.out.println(i + " " + (a - i));
                    break;
                }
            }

        }
    }

    //소수 판별
    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}