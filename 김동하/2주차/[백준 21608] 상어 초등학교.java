package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n;
    public static List<Integer>[] prior = new ArrayList[401];
    public static int order[] = new int[401];
    public static int[][] arr = new int[21][21];
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static int ans = 0;
    public static int[] value = {0,1,10,100,1000};

    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        int[] in;
        for (int i = 1; i <= n * n; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = in[0];
            order[i] = x;
            prior[x] = new ArrayList<>();
            for (int j = 1; j <= 4; j++) {
                prior[x].add(in[j]);
            }
        }
    }

    static class Node {

        private int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.x != o2.x) {
                return Integer.compare(o1.x, o2.x); // x값 기준으로 비교
            }
            return Integer.compare(o1.y, o2.y); // x값이 같다면 y값 기준으로 비교
        }
    }


    public static boolean OOB(int x, int y) {
        if (x > n || x < 1 || y > n || y < 1) {
            return true;
        }
        return false;
    }

    public static int countEmpty(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (OOB(nx, ny) || arr[nx][ny] != 0) {
                continue;
            }
            cnt++;
        }
        return cnt;
    }

    public static int countNearFriends(int x, int y, List<Integer> list) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (OOB(nx, ny) || !list.contains(arr[nx][ny])) {
                continue;
            }
            cnt++;
        }
        return cnt;
    }

    public static List<Node> findMaxFriends(int x) {
        List<Integer> list = prior[x];
        List<Node> tmp = new ArrayList<>();
        int maxCnt = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] == 0) {
                    int cnt = countNearFriends(i, j, list);
                    if (cnt > maxCnt) {
                        tmp = new ArrayList<>();
                        tmp.add(new Node(i, j));
                        maxCnt = cnt;
                    } else if (cnt == maxCnt) {
                        tmp.add(new Node(i, j));
                    }
                }
            }
        }
        return tmp;
    }

    public static Node findPlace(int idx) {
        List<Node> maxFriends = findMaxFriends(order[idx]);
        if (maxFriends.size() == 1) {
            return maxFriends.get(0);
        }
        List<Node> tmp = new ArrayList<>();
        int maxCnt = 0;
        for (Node maxFriend : maxFriends) {
            int cnt = countEmpty(maxFriend.x, maxFriend.y);
            if(cnt > maxCnt) {
                maxCnt = cnt;
                tmp = new ArrayList<>();
                tmp.add(maxFriend);
            }
            else if(cnt == maxCnt) {
                tmp.add(maxFriend);
            }
        }
        Collections.sort(tmp,new NodeComparator());
        return tmp.get(0);
    }

    public static void solution() {
        for(int i = 1; i <= n * n; i++) {
            Node place = findPlace(i);
            arr[place.x][place.y] = order[i];
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                int cnt = countNearFriends(i,j, prior[arr[i][j]]);
                ans += value[cnt];
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException{
        init();
        solution();
    }
}
