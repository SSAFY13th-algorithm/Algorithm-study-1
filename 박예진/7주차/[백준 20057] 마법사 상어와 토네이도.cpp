#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

/*
  알파 비율이 적혀있는 칸: 이동하지 않은 남은 모래의 양
*/

int N, cx, cy;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
int arr[500][500];
int resSand = 0;

struct Node {
  int x, y;
};

// 마지막 알파
int pdx[4][10] = {
  {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0},
  {-1, -1, 0, 0, 0, 0, 1, 1, 2, 1},
  {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0},
  {-2, -1, -1, 0, 0, 0, 0, 1, 1, -1}
};
int pdy[4][10] = {
  {0, -1, 0, 1, -2, -1, 0, 1, 0, -1},
  {-1, 1, -2, -1, 1, 2, -1, 1, 0, 0},
  {0, -1, 0, 1, 2, -1, 0, 1, 0, 1},
  {0, -1, 1, -2, -1, 1, 2, -1, 1, 0}
};
int percent[4][9] = {
  {2, 10, 7, 1, 5, 10, 7, 1, 2},
  {1, 1, 2, 7, 7, 2, 10, 10, 5},
  {2, 1, 7, 10, 5, 1, 7, 10, 2},
  {5, 10, 10, 2, 7, 7, 2, 1, 1}
};

// 토네이도 비율 계산
void tornadoCalc(int x, int y, int dir) {
  if (arr[x][y] == 0) return;

  double nowSand = arr[x][y];
  double sandSum = 0;

  for(int i = 0; i < 9; i++){
    int nx = x + pdx[dir][i];
    int ny = y + pdy[dir][i];
    int per = percent[dir][i];
    int nowRes = floor(nowSand * (per / 100.0));
    sandSum += nowRes;
    
    // 격자 밖으로 나간 모래
    if (nx < 0 || nx >= N || ny < 0 || ny >= N){
      resSand += nowRes;
    }
    else {
      arr[nx][ny] += nowRes;
    }
  }
  // 알파자리
  int alphaX = x + pdx[dir][9];
  int alphaY = y + pdy[dir][9];
  int remainSand = nowSand - sandSum;

  if (alphaX < 0 || alphaX >= N || alphaY < 0 || alphaY >= N) {
    resSand += remainSand;
  } else {
    arr[alphaX][alphaY] += remainSand;
  }
  // 원래 위치
  arr[x][y] = 0;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> N;
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        cin >> arr[i][j];
      }
    }
    cx = N / 2;
    cy = N / 2;

    int cnt = 1;
    while(cnt < N) {
      int nx, ny;
      for(int dir = 0; dir < 4; dir++){
        for(int num = 1; num <= cnt; num++){
          nx = cx + dx[dir] * num; 
          ny = cy + dy[dir] * num; 
          // 토네이도 이동 
          tornadoCalc(nx, ny, dir);
        }
        cx = nx;
        cy = ny;

        if (dir % 2 == 1) cnt++;
      }
    }
    // 마지막에 한번 더
    // 0.N-1 ~ 0.0
    for(int num = 1; num <= N - 1; num++){
      int nx = cx + dx[0] * num;
      int ny = cy + dy[0] * num;
      tornadoCalc(nx, ny, 0);
    }

    cout << resSand;
    return 0;
}
