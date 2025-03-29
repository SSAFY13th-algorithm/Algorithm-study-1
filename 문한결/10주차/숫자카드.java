package solution;

import java.util.*;
import java.io.*;

public class 숫자카드  {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    static int n, m;
    static int[] cards, validatedCards;
    static Set<Integer> sets;

    public static void main(String[] args) throws Exception {
        init();
        findAnswer();
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        sets = new HashSet<>();
        cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i : cards)
            sets.add(i);
        m = Integer.parseInt(br.readLine());
        validatedCards =
                Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    }

    static void findAnswer() {
        for (int i : validatedCards) {
            System.out.print(sets.contains(i) ? 1 + " " : 0 + " ");
        }
    }
}
