import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HJS {
    static int size;
    static boolean result;
    static String[] num1,num2,num3;
    static HashMap<String,Integer> mappingT = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        num1 = br.readLine().split("");
        num2 = br.readLine().split("");
        num3 = br.readLine().split("");
        
        // 복잡하게 짜려다가 생각해보니 그냥 다 고려해도 시간복잡도 안넘침
        process();
        print();
    }

    private static void print() {
        System.out.println((result)?"HJS! HJS! HJS!":"Hmm...");
    }

    public static void process() {

        result =
        compareThree(1,2,3) || compareThree(1,3,2)
                || compareThree(2,1,3) || compareThree(2,3,1)
                || compareThree(3,1,2) || compareThree(3,2,1);
    }

    private static boolean compareThree(int h, int j, int s) {
        mappingT.clear();
        mappingT.put("H",h);
        mappingT.put("J",j);
        mappingT.put("S",s);
        return compareTwo(num1,num2) && compareTwo(num2,num3);
    }

    private static boolean compareTwo(String[] n1, String[] n2) {
        for (int i = 0; i < size; i++) {
            if( mappingT.get(n1[i]) == mappingT.get(n2[i]) ) ;
            else if (mappingT.get(n1[i]) > mappingT.get(n2[i])) return false;
            else return true;
        }

        return false;
    }


}
