import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 제곱수의합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);

        int num = 2;
        int min = 0;

        for (int i = 4; i <= n; i++) {
            if (i == num * num) {
                num++;
                arr.add(1);
            } else {
                for (int j = num; j > 1; j--) {
                    int temp = arr.get((j - 1) * (j - 1) - 1) + arr.get(i - (j - 1) * (j - 1) - 1);
                    if (j == num || temp < min) {
                        min = temp;
                    }
                }
                arr.add(min);
            }
        }

        System.out.println(arr.get(n - 1));
    }
}
