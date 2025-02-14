import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int CITY; // 도시 개수
    private static long[] ROADS; // 도로 길이 배열
    private static long[] PRICES; // 주유소 가격 배열

    public static void main(String[] args) throws IOException {

        init();

        long totalCost = calculate();
        System.out.println(totalCost);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도시 개수 입력
        CITY = Integer.parseInt(br.readLine());

        // 도로 길이 입력
        ROADS = new long[CITY - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < CITY - 1; i++) {
            ROADS[i] = Long.parseLong(st.nextToken());
        }

        // 주유소 가격 입력
        PRICES = new long[CITY];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < CITY; i++) {
            PRICES[i] = Long.parseLong(st.nextToken());
        }
    }

    private static long calculate() {
        long totalCost = 0;  // 총 최소 비용 (처음에는 0원)

        // 첫 번째 도시의 주유소 가격을 기준으로 시작
        long cheapestFuelPrice = PRICES[0];

        // 모든 도로를 따라 이동하면서 최소 비용을 계산
        for (int cur = 0; cur < CITY - 1; cur++) {
            if (PRICES[cur] < cheapestFuelPrice) {
                totalCost += PRICES[cur] * ROADS[cur];
                cheapestFuelPrice = PRICES[cur];
            } else {
                totalCost += cheapestFuelPrice * ROADS[cur];
                cheapestFuelPrice = Math.min(cheapestFuelPrice, PRICES[cur+1]);
            }
        }
        return totalCost;
    }

}
