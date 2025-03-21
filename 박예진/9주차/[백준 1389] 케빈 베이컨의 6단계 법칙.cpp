#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

/*
	최소 몇 단계만에 이어질 수 있는지 계산
	모든 사람들 다 계싼해보기
	케빈 베이컨 수 가장 적은 사람

	모든 노드 -> 모든 노드
	모든 경우의 수 플로이드 와샬
*/

int N, M;
int graph[101][101];
bool visited[101];
const int INF = 1e9;

void floyd() {
	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				if (graph[i][j] > graph[i][k] + graph[k][j]) {
					graph[i][j] = graph[i][k] + graph[k][j];
				}
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (i != j) graph[i][j] = INF;
		}
	}

	while (M-- > 0) {
		int u, v;
		cin >> u >> v;
		graph[u][v] = 1;
		graph[v][u] = 1;
	}

	floyd();

	int answer = 0;
	int res = 1e9;
	for (int i = 1; i <= N; i++) {
		int sum = 0;
		for (int j = 1; j <= N; j++) {
			sum += graph[i][j];
		}

		if (res > sum) {
			res = sum;
			answer = i;
		}
	}

	cout << answer;
	

	return 0;
}
