#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <stack>
#include <map>
using namespace std;

int arr[1000001];
long long preSum[1000001];
int mod[1000001];

// nC2
long long combi(long long n) {
	return n * (n - 1) / 2;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	int N, M;
	cin >> N >> M;

	preSum[0] = 0;
	mod[0] = 0;

	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
		preSum[i] = preSum[i - 1] + arr[i];
		mod[i] = preSum[i] % M;
		if (mod[i] < 0) mod[i] += M;
	}

	map<int, long long> m; // 공통된 나머지 개수 세기
	for (int i = 0; i <= N; i++) {
		m[mod[i]]++;
	}

	long long res = 0;
	for (auto iter : m) {
		res += combi(iter.second);
	}

	cout << res;

	return 0;
}
