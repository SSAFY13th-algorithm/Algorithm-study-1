package solution;
import java.util.*;
import java.io.*;



// 각 칸은 가로아니면 세로에 포함됨
// 가로: 1 세로:0 전체 경우의 수: 1<<(n*m)
public class 종이조각{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[] input;
    static int arr[][];
    static int answer=0;
    static int sum=0;
    static String temp="";

    public static void main(String[] args) throws IOException {
        init();
        execute();
        System.out.println(answer);


    }

    static void init() throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n=input[0];
        m=input[1];
        arr=new int[n][m];
        for(int i=0;i<n;i++) {
            input = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for(int j=0;j<m;j++)arr[i][j]=input[j];
        }
    }
    
    static void execute() {
    	for(int i=0;i<(1<<(n*m));i++) {
    		sum=0;
    		temp="";
    		for(int j=0;j<n;j++) {
        		temp="";
    			for(int k=0;k<m;k++) {
    				int pos=k+j*m;
    				if(((1<<pos)&i)>0) {
    					temp=temp+String.valueOf(arr[j][k]);
    				}else {
    					if(!temp.equals(""))sum+=Integer.valueOf(temp);
    					temp="";
    				}
    			}
				if(!temp.equals(""))sum+=Integer.valueOf(temp);

    		}
    		for(int j=0;j<m;j++) {
        		temp="";
    			for(int k=0;k<n;k++) {
    				int pos=k*m+j;
    				if(((1<<pos)&i)==0) {
    					temp=temp+String.valueOf(arr[k][j]);
    				}else {
    					if(!temp.equals(""))sum+=Integer.valueOf(temp);
    					temp="";
    				}
    			}
				if(!temp.equals(""))sum+=Integer.valueOf(temp);

    		}
    		answer=Math.max(answer, sum);
    		
    	}
    }
}



