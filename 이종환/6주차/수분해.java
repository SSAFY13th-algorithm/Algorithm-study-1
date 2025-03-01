import java.util.Scanner;

public class 수분해 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		
		
		// 4 -> 4
		// 5 -> 6
		// 7 -> 12
		// 8 -> 18
		// 9 -> 27
		
		if (input <= 4) {
			System.out.println(input);
			return;
		}
		
		int ans = 1;
		
		while ( input > 4  ) {
			ans *= 3;
			input -= 3;
			
			ans %= 10007;
		}
		
		ans *= input;
		ans %= 10007;
		
		System.out.println(ans);
		
		
	}
}
