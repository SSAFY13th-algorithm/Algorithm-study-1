package solution;
import java.util.*;
import java.io.*;

public class 전화돌리기
{
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int n,m;
    static List<Set<Integer>> connectList;
    static int []inOrder;
    static int[] input;
    static int answer=0;
    static int start=0;
	

	public static void main(String args[]) throws Exception
	{
        init();
        execute();
        System.out.println(answer);
	
		
	}
    static void init() throws IOException{
    	input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
    			.toArray();
    	n=input[0]; m=input[1];
    	inOrder=new int[n+1];
    	connectList=new ArrayList();
    	for(int i=0;i<=n;i++) {
    		connectList.add(new HashSet());
    	}
    	for(int i=0;i<m;i++) {
    	   	input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
        			.toArray();
    	   	connectList.get(input[1]).add(input[0]);
    	}
    	for(int i=1;i<=n;i++) {
    		for(int j: connectList.get(i)) {
    			inOrder[j]++;
    		}
    	}
  

      
    }
	static void execute() {
		Queue<Integer> que=new PriorityQueue();
		for(int i=1;i<=n;i++) {
			if(inOrder[i]==0)que.add(i);
		}
		if(que.size()==0) {
			System.out.println(0);
			System.exit(0);
		}
		answer=que.size();
		while(!que.isEmpty()) {
			int v=que.remove();
			for(int i:connectList.get(v)) {
				inOrder[i]--;
				if(inOrder[i]==0) {
					que.add(i);
					answer++;
				}
				
			}
		
			
		}
      

		 
      
	}

}