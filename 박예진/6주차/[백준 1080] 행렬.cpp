#include <iostream>
#include <algorithm>
#include <string>
using namespace std;

/*
	0과 1로만 이루어진 행렬 A와 B
	행렬 A를 B로 바꾸는데 필요한 연산의 횟수의 최솟값

	행렬 변환 연산 3*3 모든 원소 뒤집기
	3*3보다 작다면 못 뒤집음
*/

int N, M; // 행렬의 크기
int arr[51][51];
int newArr[51][51];
int answer;

void flip(int sx, int sy) {
	int ex = sx + 2;
	int ey = sy + 2;

	if (ex < N && ey < M) {
		for (int i = sx; i <= ex; i++) {
			for (int j = sy; j <= ey; j++) {
				arr[i][j] = 1 - arr[i][j]; // 0이면 1, 1이면 0으로 변경
			}
		}
		answer++;
	}
}

// 다른 게 있다면 3*3 모든 원소 뒤집기
void solution() {
	for (int i = 0; i <= N - 3; i++) {
		for (int j = 0; j <= M - 3; j++) {
			if (arr[i][j] != newArr[i][j]) {
				flip(i, j);
			}
		}
	}
}

// 같지 않은 것이 있는지 확인
bool check() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (arr[i][j] != newArr[i][j]) {
				return false;
			}
		}
	}
	return true; 
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < str.size(); j++) {
			arr[i][j] = str[j] - '0'; 
		}
	}
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < str.size(); j++) {
			newArr[i][j] = str[j] - '0';
		}
	}
	
	if (N < 3 || M < 3) {
		answer = (check() ? 0 : -1);
	}
	else {
		solution();
		// 다 돌렸는데 같지 않은 것이 있을 경우
		if (!check()) answer = -1;
	}
	cout << answer;

	return 0;
}
