import java.util.*;
import java.io.*;
public class LectureRoom {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int n,k;
    static int[] input;
    static int[] arr;
    static int[] difArr;
    static int answer=0;
    public static void main(String[] args) throws Exception{
        init();
        execute();
        System.out.println(answer);


    }
    static void init() throws Exception{
        input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n=input[0]; k=input[1];
        arr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        difArr=new int[n];


    }
    static void execute(){
        Arrays.sort(arr);
        for(int i=0;i<n;i++)difArr[i]=arr[i]-(i==0?0:arr[i-1]);
        Arrays.sort(difArr);
        for(int i=0;i<n-k;i++)answer+=difArr[i];


    }
}
