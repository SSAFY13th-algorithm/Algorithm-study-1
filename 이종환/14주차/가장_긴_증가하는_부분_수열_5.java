import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 가장_긴_증가하는_부분_수열_5 {
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] arr,record,ans;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        size = Integer.parseInt(br.readLine());
        arr = new int[size];
        record = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) arr[i] = Integer.parseInt(st.nextToken());

        getLIS();

        ans = new int[list.size()];
        getAnsArr();

        System.out.println(ans.length);
        for (int i = 0; i < ans.length; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void getAnsArr() {
        int idx = list.size()-1;
        for (int i = size-1; i >= 0; i--) {
            if (record[i]==idx) {
                ans[idx] = arr[i];
                idx--;
            }
            if (idx == -1) break;
        }

    }

    private static void getLIS() {
        list.add(arr[0]);

        for (int i = 1; i < size; i++) {
            int target = arr[i];

            int idx = binarySearch(target);
            if (idx == list.size()) list.add(target);
            else list.set(idx, target);

            record[i] = idx;
        }
    }

    private static int  binarySearch(int target) {
        int l = 0;
        int r = list.size();

        int mid = (l+r)/2;

        while(l < r) {
            if ( list.get(mid) >= target) {
                r = mid;
            } else {
                l = mid+1;
            }
            mid = (l+r)/2;
        }
        return l;
    }
}
