package solution;


import java.util.*;
import java.io.*;
public class 이모티콘 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int answer;
    static boolean[][] visited = new boolean[2001][2001];

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		init();
		execute();
		System.out.println(answer);

	}
	static void init() throws Exception{
		n=Integer.parseInt(br.readLine());
	
		
		
	}
	static void execute() {
		Queue<int []>que=new ArrayDeque();
		que.add(new int[] {1,0,0});
		visited[1][0]=true;
		
		while(!que.isEmpty()) {
			int arr[]=que.remove();
			int emoN=arr[0]; int clip=arr[1]; int time=arr[2];
			if(emoN==n) {
				answer=time;
				return;
			}
			if(OOB(emoN,emoN) && !visited[emoN][emoN]) {
			que.add(new int[] {emoN,emoN,time+1});
			visited[emoN][emoN]=true;
			}
			if(clip!=0 && OOB(emoN+clip,clip) 
					&& !visited[emoN+clip][clip]) {
				que.add(new int[] {emoN+clip,clip,time+1});
				visited[emoN+clip][clip]=true;
			}
			if(OOB(emoN-1,clip) && !visited[emoN-1][clip]) {
			que.add(new int[] {emoN-1,clip,time+1});
			visited[emoN-1][clip]=true;
			}
		}
	
		
	}

	static boolean OOB(int x,int y) {
		return !(x>=(n+1) || x<0 || y>=n || y<0);
	}

}
