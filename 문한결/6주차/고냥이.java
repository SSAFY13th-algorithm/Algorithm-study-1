import java.util.*;
import java.io.*;
public class 고냥이 {
	 static int N;
	    static String str[];
	    static Set<String> sets=new HashSet(); 
	    static Map<String,Integer> maps=new HashMap();
	    static int answer=1;
		static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		public static void main(String[] args) throws NumberFormatException, IOException {
			// TODO Auto-generated method stub
			N=Integer.parseInt(br.readLine());
			str=br.readLine().trim().split("");

			findAnswer();
			if(str.length==1)answer=1;
			System.out.println(answer);

		}
		
		public static void findAnswer() {
			int start=0;
			int end=0;
			while(start<=end && end<str.length) {
				if(sets.size()<=N) {
					answer=Math.max(answer, end-start);
					sets.add(str[end]);
					if(maps.get(str[end])==null) {
						maps.put(str[end],1);
					}
					else maps.put(str[end], maps.get(str[end])+1);
					end+=1;

				}
				else {
					maps.put(str[start],maps.get(str[start])-1);
					if(maps.get(str[start])==0) {
						sets.remove(str[start]);
						maps.remove(str[start]);
					}
					start+=1;
					
				}
			}
			if(sets.size()<=N)				answer=Math.max(answer, end-start);

		}

	}
