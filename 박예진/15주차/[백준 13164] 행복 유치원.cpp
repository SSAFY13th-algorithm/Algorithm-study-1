#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, K;
int arr[300001];

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	int sum = 0;
	cin >> N >> K;
	vector<int> v;
	cin >> arr[0];
	for (int i = 1; i < N; i++) {
		cin >> arr[i];
		v.push_back(arr[i] - arr[i - 1]);
	}
	sort(v.begin(), v.end());

	for (int i = 0; i < N - K; i++) {
		sum += v[i];
	}
	cout << sum;

	return 0;
}
