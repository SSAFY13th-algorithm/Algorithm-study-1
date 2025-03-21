#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

/*
	(N, 1) (N, 2), (N-1, 1), (N-1, 2) 비구름 생성
	이동 M번 명령 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 1 3 5 7

	구름칸
	물증가한칸
*/

struct Node {
	int x, y;
};

int N, M;
int arr[51][51];

int dx[8] = { 0, -1, -1, -1, 0, 1, 1, 1 };
int dy[8] = { -1, -1, 0, 1, 1, 1, 0, -1 };

bool OOB(int x, int y) {
	return x < 0 || x >= N || y < 0 || y >= N;
}

int waterSum() {
	int sum = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			sum += arr[i][j];
		}
	}
	return sum;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	vector <Node> cloud; // 구름칸
  // 초기 구름 생성
	for (int i = N - 2; i <= N - 1; i++) {
		for (int j = 0; j <= 1; j++) {
			cloud.push_back({ i, j });
		}
	}

	vector <Node> plus; // 증가한칸
	while (M-- > 0) {
		int d, s; // 방향, 몇칸이동
		cin >> d >> s;

		// 1. 구름칸 방향으로 이동
		for(Node now : cloud) {
			int nx = (now.x + dx[d - 1] * (s % N) + N) % N;
			int ny = (now.y + dy[d - 1] * (s % N) + N) % N;
			arr[nx][ny] += 1; // 이동후 비내리기
			plus.push_back({ nx, ny });
		}
		// 2. 구름칸 삭제
		cloud.clear();

		// 3. 물복사버그, 대각선 방향 거리 1인칸에 물이 있는 바구니 수만큼
		for (Node now : plus) {
			int cnt = 0;
			for (int i = 1; i < 8; i += 2) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (!OOB(nx, ny) && arr[nx][ny] != 0) {
					cnt++;
				}
			}
			arr[now.x][now.y] += cnt;
		}

		// 4. 바구니 저장된 물의 양 2이상인칸 모든 칸에 구름 생성, 물의 양 2 줄어듦
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bool flag = true;
				for (Node node : plus) {
					if (node.x == i && node.y == j) {
						flag = false;
						break;
					}
				}
				// 구름칸 아닐 때, 물 2이상
				if (flag && arr[i][j] >= 2) {
					arr[i][j] -= 2;
					cloud.push_back({ i, j });
				}
			}
		}

		plus.clear(); 
		
	}

	cout << waterSum();

	return 0;
}
