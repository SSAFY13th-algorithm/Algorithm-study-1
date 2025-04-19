package solution;


import java.util.*;
import java.io.*;
public class 세수 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr;
    static List<Integer> list;
    static Set<Integer> kzSet;
    static Map<Integer,Integer> maps;
    static int n;
    static int answer=0;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		init();
		execute();
		System.out.println(answer);

	}
	static void init() throws Exception{
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		list=new ArrayList();
		maps=new HashMap();
		kzSet=new HashSet();
		Arrays.sort(arr);
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++){
				list.add(arr[i]+arr[j]);
				kzSet.add(arr[j]-arr[i]);
				if(maps.get(arr[j]-arr[i])==null)
					maps.put(arr[j]-arr[i], arr[j]);
				else maps.put(arr[j]-arr[i], 
						Math.max(maps.get(arr[j]-arr[i]),arr[j]));
			}
		}
		
		
	}
	static void execute() {
		for(int i: list) {
			if(kzSet.contains(i))answer=Math.max(answer, 
					maps.get(i));
		}
		
	}

}
