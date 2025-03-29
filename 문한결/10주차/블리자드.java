package solution;
import java.util.*;
import java.io.*;


public class 블리자드  {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[][] blizard, maps;
    static int[] input;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int startX;
    static int startY;
    static List<Integer> list;
    static List<Group> groups;
    static int answer[] = new int[4];



    public static void main(String[] args) throws IOException {
        init();
        execute();
        System.out.println(answer[1]+2*answer[2]+3*answer[3]);


    }

    static void init() throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        maps = new int[n][n];
        blizard = new int[m][2];
        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++)
                maps[i][j] = input[j];
        }
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            blizard[i][0] = input[0];
            blizard[i][1] = input[1];
        }
        startX = startY = (n + 1) / 2 - 1;
    }

    static void execute() {
        for (int i = 0; i < m; i++) {
            int d = convertDir(blizard[i][0]);
            int s = blizard[i][1];
            goBlizard(d, s);
            moveBall();
         
            explode();
      
            insertBall();
        }
    }

    static int convertDir(int d) {
        switch (d) {
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return 0;
            case 4:
                return 2;
        }
        return 0;
    }

    static void goBlizard(int d, int s) {
        for (int i = 1; i < s + 1; i++) {
            if (startX + i * dx[d] >= 0 && startX + i * dx[d] < n && startY + i * dy[d] >= 0
                    && startY + i * dy[d] < n)
                maps[startY + i * dy[d]][startX + i * dx[d]] = 0;
        }
    }

    static void moveBall() {
        list = new ArrayList<>();
        int curX = startX;
        int curY = startY;
        int cnt = 0;
        int dir = 0;
        int conti = 0;
        int convertNum = 1;
        int ccc = 0;
        while (cnt < n * n - 1) {
            curX += dx[dir];
            curY += dy[dir];
            if (maps[curY][curX] != 0)
                list.add(maps[curY][curX]);
            conti += 1;
            cnt++;
            if (conti == convertNum) {
                dir = (dir + 1) % 4;
                conti = 0;
                ccc += 1;
            }
            if (ccc == 2) {
                ccc = 0;
                convertNum++;
            }

        }
    }

    static void explode() {
        groups = new ArrayList<>();
       
        for (int i : list) {
            if (groups.size() > 0 && groups.get(groups.size() - 1).value == i) {
                groups.get(groups.size() - 1).n++;
            } else {
                groups.add(new Group(i, 1));
            }
        }
     
        List<Group> temps = new ArrayList<>();
        for (Group g : groups)
            temps.add(g);
        
        int cnt = 0;
        
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).n >= 4) {
                answer[groups.get(i).value] += groups.get(i).n;
                temps.remove(i - cnt);
                cnt++;
            }
        }
        
        groups = temps;
   
        boolean isFinish = false;
        while (!isFinish) {
            isFinish = true;
            List<Group> lists = new ArrayList<>();
            for (Group g : groups) {
                if (lists.size() > 0 && lists.get(lists.size() - 1).value == g.value) {
                    lists.get(lists.size() - 1).n += g.n;
                } else {
                    lists.add(new Group(g.value, g.n));
                }
            }

            temps = new ArrayList<>();
            for (Group g : lists)
                temps.add(new Group(g.value,g.n));
           
            cnt = 0;
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(i).n >= 4) {
                    temps.remove(i - cnt);
                    cnt++;
                    answer[lists.get(i).value] += lists.get(i).n;
                    isFinish = false;
                }
            }
            groups = temps;
        }
      


    }

    static void insertBall() {
        int curX = startX;
        int curY = startY;
        int cnt = 0;
        int dir = 0;
        int conti = 0;
        int convertNum = 1;
        int ccc = 0;
        maps=new int[n][n];
        while (cnt < n * n - 1) {
            curX += dx[dir];
            curY += dy[dir];
            if(cnt/2>=groups.size())break;
            if (cnt % 2 == 0)
                maps[curY][curX] = groups.get(cnt/2).n;
            else
                maps[curY][curX] = groups.get(cnt/2).value;
            conti += 1;
            cnt++;
            if (conti == convertNum) {
                dir = (dir + 1) % 4;
                conti = 0;
                ccc += 1;
            }
            if (ccc == 2) {
                ccc = 0;
                convertNum++;
            }

        }
        
    }


}


class Group {
    int n;
    int value;

    public Group(int v, int n) {
        this.n = n;
        this.value = v;
    }

}


