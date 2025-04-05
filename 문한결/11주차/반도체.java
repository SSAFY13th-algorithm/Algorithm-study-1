package solution;

import java.util.*;
import java.io.*;
public class 반도체 {

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int [] arr;
    static TreeSet <Integer> sets;
    static int answer=0;

    static List<int[]> listPath;



    public static void main(String[] args)throws Exception {
        init();
        execute();
        
        System.out.println(sets.size());

        
    }

    static void init() throws IOException{
        n=Integer.parseInt(br.readLine());
        arr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sets=new TreeSet<>();
      
    }

    static void execute(){
        for (int i = 0; i < n; i++) {
            Integer ceiling = sets.ceiling(arr[i]);
            if (ceiling != null) {
                sets.remove(ceiling); // 더 큰 값 교체
            }
            sets.add(arr[i]); // 새로운 값 넣기
        }
      

    }
    
}



