package solution;

import java.util.*;
import java.io.*;



public class 강의실 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] input;
    static int arr[][];
    static int answer=0;
    static Queue<Integer> priorQue;



    public static void main(String[] args) throws IOException {
        init();
        execute();
        System.out.println(answer);


    }

    static void init() throws IOException {
    	n=Integer.parseInt(br.readLine());
    	arr=new int[n][2];
        for(int i=0;i<n;i++) {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        arr[i][0]=input[0];
        arr[i][1]=input[1];
        }
        Arrays.sort(arr,Comparator.comparingInt(arr1->arr1[0]));
        priorQue=new PriorityQueue();
     
    }
    
    static void execute() {
    for(int i=0;i<n;i++) {
    	if(priorQue.peek()==null || priorQue.peek()>arr[i][0]) answer++;
    	else priorQue.remove();
    	
    
    	priorQue.add(arr[i][1]);
    }
    }
}




