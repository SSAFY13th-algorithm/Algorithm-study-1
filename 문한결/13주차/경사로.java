package solution;

import java.util.*;
import java.io.*;

public class 경사로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr;
    static int n, xx,t;
    static int answer = 0;

    public static void main(String[] args) throws Exception {

      
            init();
            execute();
            System.out.println(answer);
        
    }

    static void init() throws Exception {
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        xx = input[1];
        arr = new int[n][n];
        answer=0;
        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) arr[i][j] = input[j];
        }
    }

    static void execute() {
        for (int i = 0; i < n; i++) {
            if (isRunWayRow(i)) answer++;
            if (isRunWayCol(i)) answer++;
        }
    }

    static boolean isRunWayRow(int row) {
        int count = 1;
        for (int i = 1; i < n; i++) {
            int diff = arr[row][i] - arr[row][i - 1];
            if (diff == 0) {
                count++;
            } else if (diff == 1) { 
                if (count < xx) return false;
                count = 1;
            } else if (diff == -1) { 
                if (!checkDown(arr[row], i)) return false;
                i += xx - 1;
                count = 0;
            } else return false;
        }
        return true;
    }

    static boolean isRunWayCol(int col) {
        int count = 1;
        for (int i = 1; i < n; i++) {
            int diff = arr[i][col] - arr[i - 1][col];
            if (diff == 0) {
                count++;
            } else if (diff == 1) {
                if (count < xx) return false;
                count = 1;
            } else if (diff == -1) {
                if (!checkDownCol(col, i)) return false;
                i += xx - 1;
                count = 0;
            } else return false;
        }
        return true;
    }

    static boolean checkDown(int[] line, int start) {
        int height = line[start];
        for (int i = start; i < start + xx; i++) {
            if (i >= n || line[i] != height) return false;
        }
        return true;
    }

    static boolean checkDownCol(int col, int start) {
        int height = arr[start][col];
        for (int i = start; i < start + xx; i++) {
            if (i >= n || arr[i][col] != height) return false;
        }
        return true;
    }
}

