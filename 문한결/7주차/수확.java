import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class 수확 {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    static int answer=0;
    static int[][] arr;
    static int [] input;
    static int N;
    static int dummy;
    static int [][]sumRange;
    

	
	public static void main(String[] args) throws IOException {
		N=Integer.parseInt(br.readLine());
		input=new int[N];
        for(int i=0;i<N;i++) {
        	dummy=Integer.parseInt(br.readLine());
        	input[i]=dummy;
        }
        arr=new int[N][N];
        initialize();
        findAnswer();
        System.out.println(arr[0][N-1]);
	}
	static void initialize() {
		for(int i=0;i<N;i++) {
			arr[i][i]=input[i];
		}
	}
	static void findAnswer() {
		for(int i=1;i<N;i++) {
			for(int j=i-1;j>=0;j--) {
				int maxN=Math.max(arr[j][i-1], arr[j+1][i]);
				int sum=0;
				for(int k=j;k<=i;k++) {
					sum+=input[k];
				}
				arr[j][i]=sum+maxN;
			}
		}
	}
	
	

	
}
