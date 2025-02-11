import java.util.*;
import java.io.*;

/*
 * 물고기 번호 작은순대로 1칸 이동
 * 이동 o) 빈칸, 다른 물고기 칸 swap
 * 이동 x) 상어, 경계넘음
 * 
 * 상어 이동
 * 한번에 여러칸 이동 가능 백트래킹
 * 물고기칸만 이동 o
 * 먹으면 방향 얻음
 * 
 *  ↑, ↖, ←, ↙, ↓, ↘, →, ↗
 * */

public class Main {
	
	static int[][][] arr; // x, y, 0 값, 1 방향
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int maxSum = 0;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 입력
        arr = new int[4][4][2];
        for(int i = 0; i < 4; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < 4; j++) {
        		int idx = Integer.parseInt(st.nextToken());
        		int dir = Integer.parseInt(st.nextToken()) - 1;
        		arr[i][j][0] = idx;
        		arr[i][j][1] = dir;
        	}
        }
         
        // 해결       
        // Shark 초기화
        int sharkIdx = arr[0][0][0];
        int sharkDir = arr[0][0][1];
        arr[0][0][0] = -1; // 처음에 먹고 시작함
        dfs(0, 0, sharkDir, sharkIdx, arr);
        
        // 결과
        System.out.println(maxSum);
    }
    
    static void dfs(int x, int y, int dir, int sum, int[][][] arr) {
    	maxSum = Math.max(maxSum, sum);
    	
    	// 깊은 복사
    	int[][][] copyArr = new int[4][4][2];
    	for(int i = 0; i < 4; i++) {
    		for(int j = 0; j < 4; j++) {
    			copyArr[i][j][0] = arr[i][j][0]; // idx
    			copyArr[i][j][1] = arr[i][j][1]; // dir
    		}
    	}
    	
    	// 물고기 이동
    	fishMove(copyArr);
    	
	   	// 상어 이동
	   	/* 한번에 여러칸 이동 가능, 물고기칸만 이동 o
	   	 * 먹으면 방향 얻음 */
    	for(int i = 1; i < 4; i++) {
    		int nx = x + dx[dir] * i;
    		int ny = y + dy[dir] * i;
    		
    		// 경계 벗어남, 빈칸
    		if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || copyArr[nx][ny][0] == 0) continue;
    		
    		int fishIdx = copyArr[nx][ny][0];
    		int fishDir = copyArr[nx][ny][1];
    		
    		// 이동후 물고기 먹기
    		copyArr[x][y][0] = 0; // 물고기 먹음
    		copyArr[nx][ny][0] = -1; // 상어 
    		dfs(nx, ny, fishDir, sum + fishIdx, copyArr);
    		copyArr[x][y][0] = -1; // 상어 ???
    		copyArr[nx][ny][0] = fishIdx; // 물고기
    	}
    }
    
    static void fishMove(int[][][] arr) {
    	/* 물고기 이동
    	 * 이동 x) 상어, 경계넘음 -> 이동할 때까지 반시계 회전
    	 * 이동 o) 빈칸, 다른 물고기 칸 swap
    	 * */
    	for(int k = 1; k <= 16; k++) {
    		// 현재 물고기 위치 찾기
    		int x = -1, y = -1, dir = -1;
    		for(int i = 0; i < 4; i++) {
    			for(int j = 0; j < 4; j++) {
    				if (arr[i][j][0] == k) {
    					x = i;
    					y = j;
    					dir = arr[i][j][1];
    				}
    			}
    		}
    		if (x == -1) continue; // 이미 먹힘
    		
    		int nx = x + dx[dir];
    		int ny = y + dy[dir];
    		
    		// 이동 x 경계 벗어남  or 상어 -> 이동할 때까지 반시계 회전
    		int cnt = 0;
    		while ((nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || arr[nx][ny][0] == -1) && cnt < 8) {
    			dir = (dir + 1) % 8;
    			nx = x + dx[dir];
    			ny = y + dy[dir];
    			cnt++;
    		}
    		// 이동 o 빈칸이나 다른 물고기 칸
    		if (cnt < 8) {
    			int tempIdx = arr[nx][ny][0];
    			int tempDir = arr[nx][ny][1];
    			
    			arr[nx][ny][0] = arr[x][y][0];
    			arr[nx][ny][1] = dir;
    			arr[x][y][0] = tempIdx;
    			arr[x][y][1] = tempDir;
    		}

    	}
    }

}
