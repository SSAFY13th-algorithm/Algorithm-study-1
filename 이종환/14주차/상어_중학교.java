import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 상어_중학교 {
    static int size, colorCnt,totalPoint,tY,tX,maxBSize,maxRbCnt;
    static int[][] arr;
    static boolean[][] visited;

    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        init();
        while (processAboutBlock()) {
            applyGravity();
            turn();
            applyGravity();
        }

        System.out.println(totalPoint);
    }

//    private static void print() {
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------");
//    }


    private static void turn(){
        int[][] temp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[size-1-j][i] = arr[i][j];
            }
        }
        arr = temp;

    }

    private static void applyGravity() {
        for (int j = 0; j < size; j++) {
            int[] temp = new int[size];
            Arrays.fill(temp, -2);
            Stack<Integer> s = new Stack<>();

            // -1 만나면 그 위로 쌓음. 이때 lifo
            for (int i = 0; i < size; i++) {
                if (arr[i][j] == -1){
                    temp[i] = -1;
                    int sSize = s.size();
                    for (int k = 1; k <= sSize; k++) {
                        temp[i-k] = s.pop();
                    }
                } else{
                    if (arr[i][j] != -2) s.add(arr[i][j]);
                }
            }
            int sSize = s.size();
            for (int k = 1; k <= sSize; k++) {
                temp[size-k] = s.pop();
            }

            for (int i = 0; i < temp.length; i++) {
                arr[i][j] = temp[i];
            }

        }
    }

    private static boolean processAboutBlock() {
        maxBSize = 0;
        maxRbCnt = 0;
        visited = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(arr[i][j] > -1 && arr[i][j] != 0 && !visited[i][j]){
                    findBiggestBlock(i,j,arr[i][j]);
                }
            }
        }

        if (maxBSize < 2) return false;
        removeBlock();
        totalPoint += maxBSize*maxBSize;
        return true;
    }

    private static void findBiggestBlock(int y, int x, int target) {
        int curSize = 1;
        int curRbCnt = 0;
        boolean[][] tempVisited = new boolean[size][size];

        Queue<Point> q = new LinkedList<>();
        visited[y][x] = true;
        tempVisited[y][x] = true;
        q.add(new Point(x,y));
        while(!q.isEmpty()){
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];
                if(ny < 0 || ny >= size || nx < 0 || nx >= size) continue;
                if(arr[ny][nx]  < 0 || arr[ny][nx] != 0 && arr[ny][nx] != target ) continue;
                if(tempVisited[ny][nx]) continue;


                visited[ny][nx] = true;
                tempVisited[ny][nx] = true;
                curSize++;
                if(arr[ny][nx] == 0) curRbCnt++;

                q.add(new Point(nx,ny));
            }
        }

        if( curSize > maxBSize || (curSize == maxBSize && curRbCnt >= maxRbCnt)){
            maxBSize = curSize;
            maxRbCnt = curRbCnt;
            tY = y;
            tX = x;
        }

    }

    private static void removeBlock() {
        boolean[][] tempVisited = new boolean[size][size];

        Queue<Point> q = new LinkedList<>();
        int target = arr[tY][tX];
        arr[tY][tX] = -2;
        q.add(new Point(tX,tY));
        while(!q.isEmpty()){
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];
                if(ny < 0 || ny >= size || nx < 0 || nx >= size) continue;
                if(arr[ny][nx]  < 0 || arr[ny][nx] != 0 && arr[ny][nx] != target ) continue;
                if(tempVisited[ny][nx]) continue;

                arr[ny][nx] = -2;
                tempVisited[ny][nx] = true;

                q.add(new Point(nx,ny));
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        colorCnt = Integer.parseInt(st.nextToken());
        arr = new int[size][size];
        totalPoint = 0;

        for(int i = 0; i < size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < size; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
