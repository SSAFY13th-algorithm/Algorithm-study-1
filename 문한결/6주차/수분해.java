import java.util.*;
import java.io.*;
public class 수분해 {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static long answer=0;
    static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		N=Integer.valueOf(br.readLine());
		findAnswer();
		System.out.println(answer%10007);

	}
	public static void findAnswer() {
		long sum=1;
		long a=N/3;
		a--;
		if(N==0)return;
		for(int i=0;i<a;i++) {
			sum=(sum*3)%10007;
		}
		long b=N-3*a;
		if (b%3==0){
			sum=(sum*3)%10007;
		}else if(b%3==1){
			if(a!=-1) sum=(sum*4)%10007;	
			else sum=sum*1;
		}else if(b%3==2) {
			if(a!=-1) sum=(sum*6)%10007;
			else sum=sum*2;

		}
		answer=sum;
		
		
	}

}
