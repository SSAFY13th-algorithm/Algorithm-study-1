import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();

        int blen = bomb.length();

        for (char c : str.toCharArray()) {
            sb.append(c);

            if (sb.length() >= blen) {
                if (sb.substring(sb.length() - blen).equals(bomb)) {
                    sb.delete(sb.length()-blen,sb.length());
                }
            }
        }


        if (sb.length() != 0) {
            System.out.println(sb.toString());
        } else {
            System.out.println("FRULA");
        }
    }


}
