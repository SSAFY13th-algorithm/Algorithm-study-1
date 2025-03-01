#include <iostream>
#include <algorithm>
using namespace std;

/*
	유니온파인드
	합집합 0 a b
	같은 집합 확인 1 a b
*/

int n, m;
int parent[1000001];

int getParent(int n) {
	if (parent[n] == n) return n;
	return parent[n] = getParent(parent[n]);
}

void unionParent(int a, int b) {
	a = getParent(a);
	b = getParent(b);

	if (a < b) parent[b] = a;
	else parent[a] = b;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n >> m;

	// 부모 초기화
	for (int i = 1; i <= n; i++) {
		parent[i] = i;
	}

	for (int i = 0; i < m; i++) {
		int idx, a, b;
		cin >> idx >> a >> b;
		// 합집합
		if (idx == 0) {
			unionParent(a, b);
		}
		// 같은 집합 확인
		else if (idx == 1) {
			bool flag = getParent(a) == getParent(b);
			cout << (flag ? "YES\n" : "NO\n");
		}
	}

	return 0;
}
