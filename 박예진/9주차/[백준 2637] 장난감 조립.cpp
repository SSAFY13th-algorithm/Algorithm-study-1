#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

/*
	기본 부품: 다른 부품 사용 불가
	중간 부품: 또다른 부품이나 기본 부품 이용해서 만들어지는 부품

	완제품과 부품 관계들
	완제품을 조립하기 위해 필요한 기본 부품의 종류별 개수
*/

int N, M; // 완제품 번호, 관계들
int indegree[101];
int dp[101][101]; // i번 만들기 위해 필요한 j번 기본 부품 개수

struct Edge {
	int node, cost;
};

vector<vector<Edge>> graph;

int topology() {
	int answer = 0;
	queue <int> q;

	// 진입차수 0
	for (int i = 1; i <= N; i++) {
		if (indegree[i] == 0) {
			q.push(i);
			dp[i][i] = 1;
		}
	}

	while (!q.empty()) {
		int now = q.front();
		q.pop();

		for (auto edge : graph[now]) {
			int next = edge.node;
			int cost = edge.cost;

			for (int i = 1; i <= N; i++) {
				if (dp[now][i] > 0) {
					dp[next][i] += dp[now][i] * cost;
				}
			}

			// 진입차수 0
			indegree[next]--;
			if (indegree[next] == 0) q.push(next);
		}

	}

	return answer;
}

void result() {
	for (int i = 1; i <= N; i++) {
		if (dp[N][i] > 0) {
			cout << i << " " << dp[N][i] << "\n";
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M;
	graph.resize(N + 1);

	while (M-- > 0) {
		// (중간 부품이나 완제품) X를 만드는데 (중간 부품 혹은 기본 부품) Y가 K개 필요
		int X, Y, K;
		cin >> X >> Y >> K;
		
		graph[Y].push_back({ X, K }); // X -> Y
		indegree[X]++;
	}

	topology();

	result();

	return 0;
}
