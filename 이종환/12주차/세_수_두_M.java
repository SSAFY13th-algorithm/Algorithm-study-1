import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 세_수_두_M {
	static int[] arr;
	static int size;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		arr = new int[size];
		
		for (int i = 0; i < size; i++) arr[i]  = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		
		int max = 0;
		for (int i = 1; i< size-1; i++) max = Math.max(max, getDiff(i));
		System.out.println(max);
	}
	
	private static int getDiff(int midIdx) {
		int tempMax =  Math.abs(arr[midIdx-1]+arr[size-1] - arr[midIdx]*2);
		return Math.max(tempMax, Math.abs(arr[0] + arr[midIdx+1] - arr[midIdx]*2));
	}
}
