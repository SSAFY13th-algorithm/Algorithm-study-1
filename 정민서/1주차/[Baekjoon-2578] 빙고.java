
import java.util.*;

public class Main {

    public static int length = 5;

    public static int[][] arr = new int[length][length];

    public static int bingoCount;

    public static int round;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //배열 받기
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        //사회자가 부르는 수를 0부터 25까지 반복문 돌려서 받기
        for (int t = 0; t < length *length; t++) {
            int currentNum = sc.nextInt();
            round++;

            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    if (arr[i][j] == currentNum) {
                        arr[i][j] = -1;
                    }
                }
            }

            countBingo();

            if (bingoCount >= 3) {
                System.out.println(round);
                return;
            }
        }
    }

    private static void countBingo() {
        bingoCount = 0;


        //가로
        for (int i = 0; i < length; i++) {
            boolean isRowBingo = true;
            for (int j = 0; j < length; j++) {
                if(arr[i][j] != -1) {
                    isRowBingo = false;
                    break;
                }
            }
            if (isRowBingo) {
                bingoCount++;
            }
        }

        //세로
        for (int i = 0; i < length; i++) {
            boolean isColBingo = true;
            for (int j = 0; j < length; j++) {
                if(arr[j][i] != -1) {
                    isColBingo = false;
                    break;
                }
            }
            if (isColBingo) {
                bingoCount++;
            }
        }


        //오른쪽 대각선
        boolean isXyBingo = true;
        for (int i = 0; i < length; i++) {
            if(arr[i][length-i-1] != -1) {
                isXyBingo = false;
                break;
            }
        }
        if (isXyBingo) {
            bingoCount++;
        }

        //왼쪽 대각선
        boolean isyxBingo = true;
        for (int i = 0; i < length; i++) {
            if(arr[i][i] != -1) {
                isyxBingo = false;
                break;
            }
        }
        if (isyxBingo) {
            bingoCount++;
        }

    }
}
