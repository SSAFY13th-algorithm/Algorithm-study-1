import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 숫자카드 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		HashSet<Integer> cards = new HashSet<>();
		
		int cardNum = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cardNum; i++) cards.add(Integer.parseInt(st.nextToken()));
		
		int checkCnt = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i =0; i< checkCnt; i++) bw.append((cards.contains(Integer.parseInt(st.nextToken())))?"1 ":"0 ");
		
		bw.flush();
		
	}
}
