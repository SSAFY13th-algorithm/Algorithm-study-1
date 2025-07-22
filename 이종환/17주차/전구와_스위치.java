import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ������_����ġ {
	static String[] input1,input2;
	static int[] choice,flip;
	static int len,ans;
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}

	private static void print() {System.out.println((ans==Integer.MAX_VALUE)?-1:ans);}

	private static void process() {
		getFlip();
		//������ġ ����ġ�� �ȴ����� ����
		choice[0] = 0; 
		greedy();
		//������ġ ����ġ�� ������ ����
		choice[0] = 1;
		greedy();
	}


	private static void getFlip() {
		// �������� �Ǵ� �κ� ã��
		for (int i = 0; i < len; i++) flip[i] = !(input1[i].equals(input2[i]))?1:0;
		
	}

	private static void greedy() {
		for (int i = 1; i < len; i++) {
			int goal = flip[i-1]; // �̰� ���������
			int cur = (choice[i-1] + ((i > 1)?choice[i-2]:0))%2;// ���� �ΰ��������� ��Ȳ
			choice[i] = Math.abs(goal-cur);
			
		}
		
		check();
		
	}

	
	private static void check() {
		int temp = 0;
		for (int i = 0; i < len; i++) {
			temp += choice[i];
			int goal = flip[i];
			int cur = choice[i];
			
			if (i > 0) cur += choice[i-1];
			if (i < len-1) cur += choice[i+1];
			cur %= 2;

			
			if(goal != cur) return;
			
		}
		
		ans = Math.min(temp, ans);
		
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		len = Integer.parseInt(br.readLine());
		input1 = br.readLine().split("");
		input2 = br.readLine().split("");
			
		choice = new int[len];
		flip = new int[len];
		ans = Integer.MAX_VALUE;
		
	}
}
