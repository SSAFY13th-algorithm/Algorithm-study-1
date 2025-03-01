#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <queue>
#include <cmath>
using namespace std;

/*
  N*N 파이어볼 M개 발사
  위치 r c, 질량 m, 방향 d, 속력 s

  1. 파이어볼 자신의 방향 d로 속력 s칸 만큼 이동
  이동하는 중에 같은 칸에 여러 개 파이어볼 가능

  이동이 모두 끝난 뒤, 2개 이상이라면
  1. 같은 칸에 있는 파이어볼 모두 하나로 합쳐짐
  2. 파이어볼 4개의 파이어볼로 나누어짐

  1. 질량은 햡쳐진 파이어볼 질량의 합 / 5
  2. 속력은 합쳐진 파이어볼 속력의 합 / 파이어볼 개수
  3. 합쳐지는 파이어볼 방향 모두 홀수거나 짝수면 0 2 4 6 아니면 1 3 5 7
  4. 질량 0 파이어볼 소멸되어 없어짐

  이동 K번 명령후 남아있는 파이어볼 질량의 합
*/

int N, M, K; // 칸, 파이어볼 개수, 이동수
int dx[8] = { -1, -1, 0, 1, 1, 1, 0, -1 };
int dy[8] = { 0, 1, 1, 1, 0, -1, -1, -1 };

struct FireBall {
  int r, c, m, s, d;
};

queue <FireBall> FireBalls; // 파이어볼 정보
vector <FireBall> arr[51][51]; // 각 칸에 들어가있는 파이어볼

// 파이어볼 이동
void FireBallMove() {
  while (!FireBalls.empty()) {
    FireBall fb = FireBalls.front();
    FireBalls.pop();

    // 방향 음수로 될 수도 있어서
    int nx = (fb.r + dx[fb.d] * fb.s % N + N) % N;
    int ny = (fb.c + dy[fb.d] * fb.s % N + N) % N;    

    arr[nx][ny].push_back({ nx, ny, fb.m, fb.s, fb.d });
  }
}

// 이동 끝난후 각 칸에 파이어볼 2개이상인 것 찾기
void FireBallCheck() {
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      // 파이어볼 2개 이상
      if (arr[i][j].size() > 1) {
        vector<FireBall> newFireBalls(4);

        int ssum = 0, msum = 0; // 속력합, 질량합
        int oddCnt = 0, evenCnt = 0;
        for (FireBall fb : arr[i][j]) {
          ssum += fb.s;
          msum += fb.m;
          if (fb.d % 2 == 1) oddCnt++;
          else evenCnt++;
        }
        int resM = msum / 5; // 최종 질량
        int resS = ssum / arr[i][j].size(); // 최종 속력
        
        // 질량 0보다 클 경우
        if (resM > 0) {
          for (int k = 0; k < 4; k++) {
              newFireBalls[k].r = i;
              newFireBalls[k].c = j;
              newFireBalls[k].m = resM;
              newFireBalls[k].s = resS;
              newFireBalls[k].d = (oddCnt == 0 || evenCnt == 0) ? k * 2 : k * 2 + 1;
              FireBalls.push(newFireBalls[k]);
          }
        }
      }
      // FireBalls 넣어야함
      else if (arr[i][j].size() == 1) {
        FireBall fb = arr[i][j].front();
        FireBalls.push({ i, j, fb.m, fb.s, fb.d });
      }
    }
  }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> N >> M >> K;
    for (int i = 0; i < M; i++) {
      int r, c, m, s, d;
      cin >> r >> c >> m >> s >> d;
      FireBalls.push({ r - 1, c - 1, m, s, d }); // 질량, 속도, 방향 
    }

    // 이동
    while (K-- > 0) {
      FireBallMove();
      FireBallCheck();

      // arr 초기화
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          arr[i][j].clear();
        }
      }
    }

    // 남아있는 파이어볼 질량의 합
    int answer = 0;
    while (!FireBalls.empty()) {
      FireBall fb = FireBalls.front();
      FireBalls.pop();
      answer += fb.m;
    }
    cout << answer;

    return 0;
}
