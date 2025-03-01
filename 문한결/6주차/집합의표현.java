import java.util.*;
import java.io.*;
public class 집합의표현 {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static long answer=0;
    static int n,m;
    static int input[];
    static int []parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n=input[0]; m=input[1];
		parent=new int[n+1];
		for(int i=0;i<n+1;i++)parent[i]=i;
		for(int i=0;i<m;i++) {
			input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if(input[0]==0) {
				union(input[1],input[2]);
				continue;
			}
			isSameUnion(input[1],input[2]);

		}
	
		

	}
	static void union(int a,int b) {
		int a1=find(a);
		int b1=find(b);
		if(a1!=b1) {
			if(a1<b1) {
				parent[b1]=a1;
			}
			else {
				parent[a1]=b1;

			}
		}
	}
	static int find(int v) {
		if(v==parent[v])return v;
		return parent[v]=find(parent[v]);
	}
	static void isSameUnion(int a,int b) {
		if(find(a)==find(b)) {
			System.out.println("YES");
			return;
		}
		System.out.println("NO");

	}
	
	
}
