#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

/*
	T번 회전
	i번째 회전할 때 xi, di, ki
	
	번호가 x1 배수인 원판 d방향으로 k칸 회전
	d가 0인 경우 시계, 1인 경우 반시계
	원판 수가 남아있으면 인접하면서 수가 같은 거 모두 찾기
	1. 인접 수 있으면 같은 수 모두 지움
	2. 인접 수 없으면 원판 적힌 수 평균 구하고, 평균보다 큰 수에서 1빼고, 작은수 1 더함
*/

int N, M, T;

vector <vector <int>> wonpans(51, vector<int>(51, 0));

// d가 0인 경우 시계, 1인 경우 반시계
void rotate(int idx, int dir, int k) {
	// k칸 회전
	while (k-- > 0) {
		// 시계
		if (dir == 0) {
			int temp = wonpans[idx][M - 1];
			for (int j = M - 1; j > 0; j--) {
				wonpans[idx][j] = wonpans[idx][j - 1];
			}
			wonpans[idx][0] = temp;
		}
		// 반시계
		else if (dir == 1) {
			int temp = wonpans[idx][0];
			for (int j = 0; j < M - 1; j++) {
				wonpans[idx][j] = wonpans[idx][j + 1];
			}
			wonpans[idx][M - 1] = temp;
		}
	}

}

// R 원판번호
// C 원판속 번호들
bool adjCheck() {
	vector <vector <int>> temp(51, vector <int>(51, 0));
	temp = wonpans;
  
	bool flag = false; // 인접하는 수

	// 모든 행 체크
	for (int i = 1; i <= N; i++) {
		// 원판속 번호들
		for (int j = 0; j < M - 1; j++) {
			if (wonpans[i][j] <= 0 || wonpans[i][j + 1] <= 0) continue;
			if (wonpans[i][j] == wonpans[i][j + 1]) {
				temp[i][j] = temp[i][j + 1] = -1;
				flag = true;
			}
		}
		// 1열과 마지막 열 체크
		if (wonpans[i][0] == wonpans[i][M - 1]) {
			if (wonpans[i][0] <= 0 || wonpans[i][M - 1] <= 0) continue;
			temp[i][0] = temp[i][M - 1] = -1;
			flag = true;
		}
	}

	// 모든 열 체크
	for (int j = 0; j < M; j++) {
		// 원판 번호들
		for (int i = 1; i < N; i++) {
			if (wonpans[i][j] == wonpans[i + 1][j]) {
				if (wonpans[i][j] <= 0 || wonpans[i + 1][j] <= 0) continue;
				temp[i][j] = temp[i + 1][j] = -1;
				flag = true;
			}
		}
	}
	wonpans = temp;

	return flag;
}


void calc() {
	double sum = 0;
	double cnt = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 0; j < M; j++) {
			if (wonpans[i][j] > 0) {
				sum += wonpans[i][j];
				cnt++;
			}
		}
	}
	if (cnt != 0) {
		double avg = sum / cnt;
		// 평균보다 크면 - 1, 작으면 + 1
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (wonpans[i][j] > avg) {
					wonpans[i][j] -= 1;
				}
				// 작지만 0은 넘어야함 (원판에 적힌 수 1이상)
				else if (0 < wonpans[i][j] && wonpans[i][j] < avg) {
					wonpans[i][j] += 1;
				}
			}
		}
	}
}

int lastSum() {
	int sum = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 0; j < M; j++) {
			if (wonpans[i][j] > 0) sum += wonpans[i][j];
		}
	}
	return sum;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M >> T; 

	// 각 원판
	for (int i = 1; i <= N; i++) {
		// 하나의 원판에 적힌 수 
		vector <int> num(M);
		for (int j = 0; j < M; j++) {
			cin >> wonpans[i][j];
		}
	}
	// 회전수
	for (int i = 0; i < T; i++) {
		int x, d, k;
		cin >> x >> d >> k;
		// x 배수 원판, d방향, k칸 회전
		for (int j = 1; j <= N; j++) {
			if (j % x == 0) {
				rotate(j, d, k);
			}
		}
		// 원판 수 인접한지 확인하고 -1로 변경
		bool flag = adjCheck();
		// 인접수 없으면 원판 수의 평균 구해서 값 변경
		if (!flag) calc();
	}

	// 총 회전하고 원판 적힌수 합 출력
	cout << lastSum();

	return 0;
}
