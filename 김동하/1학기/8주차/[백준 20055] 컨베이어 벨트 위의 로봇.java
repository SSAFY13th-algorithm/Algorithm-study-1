import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,k;
    
    public static int dx[] = {1,0,-1,0};
    public static int dy[] = {0,1,0,-1};
    public static int arr[][];
    public static boolean isRobot[][];
    public static int cnt = 0;
    
    public static void init() throws IOException {
    	StringTokenizer stk = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(stk.nextToken());
    	k = Integer.parseInt(stk.nextToken());
    	arr = new int[2][n + 1];
    	isRobot = new boolean[2][n + 1];
    	stk = new StringTokenizer(br.readLine());
    	for(int i = 1; i <= n; i++) {
    		arr[0][i] = Integer.parseInt(stk.nextToken());
    	}
    	for(int i = n; i >= 1; i--) {
    		arr[1][i] = Integer.parseInt(stk.nextToken());
    	}
    	
    }
    
    public static void turnBelt() {
    	int tmp = arr[1][1];
    	for(int i = 1; i < n; i++) {
    		isRobot[1][i] = isRobot[1][i + 1];
    		arr[1][i] = arr[1][i + 1];
    	}
    	arr[1][n] = arr[0][n];
    	isRobot[1][n] = false;
    	arr[0][n] = arr[0][n - 1];
    	isRobot[0][n] = false;
    	for(int i = n - 2; i >= 1; i--) {
    		isRobot[0][i + 1] = isRobot[0][i];
    		arr[0][i + 1] = arr[0][i];
    	}
    	arr[0][1] = tmp;
    	isRobot[0][1] = false;
    }
    
    public static void turnRobot() {
    	isRobot[1][n] = false;
    	for(int i = n - 1; i >= 1; i--) {
    		if(isRobot[0][i] && !isRobot[0][i + 1] && arr[0][i + 1] > 0) {
    			if(i == n - 1) {
    				isRobot[0][i + 1] = false;
    				isRobot[0][i] = false;
    				arr[0][i + 1]--;
    				if(arr[0][i + 1] == 0) cnt++;
    				continue;
    			}
    			isRobot[0][i + 1] = true;
    			arr[0][i + 1]--;
    			if(arr[0][i + 1] == 0) cnt++;
    			isRobot[0][i] = false;
    		}
    	}
    	isRobot[1][1] = false;
    }
    
    public static void onloadRobot() {
    	if(arr[0][1] > 0) {
    		arr[0][1]--;
			if(arr[0][1] == 0) cnt++;
    		isRobot[0][1] = true;
    	}
    }
    
    public static void solution() throws IOException {
    	init();
    	int count = 1;
    	while(true) {
    		turnBelt();
    		turnRobot();
    		onloadRobot();
    		if(cnt >= k) {
    			sb.append(count);
    			break;
    		}
    		count++;
    	}
    	bw.append(sb.toString());
    	bw.flush();
    	bw.close();
    	br.close();
    }

    public static void main(String[] args) throws IOException {
    	solution();
    }
}