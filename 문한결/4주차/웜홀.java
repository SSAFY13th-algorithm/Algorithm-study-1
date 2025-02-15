import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;



public class Main {
  
	private static int N,M,W;
	private static LinkedList<Bridge> bridges;
	private static int[] input;
	private static int[] weights;
	private static int T;
	private static int[]visited;
 
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
     T=Integer.parseInt(br.readLine()); 
     for(int i=0;i<T;i++) {
     input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
     N=input[0]; M=input[1]; W=input[2];
     bridges=new LinkedList();
     visited=new int[N+1];
     for(int j=0;j<M;j++) {
         input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
         bridges.add(new Bridge(input[0],input[1],input[2]));
         bridges.add(new Bridge(input[1],input[0],input[2]));

     }
     for(int j=0;j<W;j++) {
         input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
         bridges.add(new Bridge(input[0],input[1],-input[2]));
         
     }
     boolean isYes=false;
     for(int j=1;j<=N;j++) {
    	 if(visited[j]==0) {
    	     weights=new int[N+1];
    		  Arrays.fill(weights, Integer.MAX_VALUE);
    		 if(bellman(j)) {
    			 System.out.println("YES");
    			 isYes=true;
    			 break;
    		 }
    	 }
     }
	 
     if(!isYes)
	 System.out.println("NO");

     
     }
    }

	private static boolean bellman(int start) {
	     weights[start]=0;
		 visited[start]=1;

		for(int j=0;j<N;j++) {
			 for(Bridge b : bridges) {
				 if(weights[b.v]!=Integer.MAX_VALUE && weights[b.w]>weights[b.v]+b.c) {
					 weights[b.w]=weights[b.v]+b.c;
					 visited[b.w]=1;

				 }
			 }
		 }
		for(Bridge b : bridges) {
			 if(weights[b.v]!=Integer.MAX_VALUE && weights[b.w]>weights[b.v]+b.c) {
		      return true;
			 }
		 }
         return false;	

	}
    
    static class Bridge{
    	int w,v,c;
    	public Bridge(int w,int v,int c) {
    		this.w=w;
    		this.v=v;
    		this.c=c;
    	}
    	
    }
	
 }



