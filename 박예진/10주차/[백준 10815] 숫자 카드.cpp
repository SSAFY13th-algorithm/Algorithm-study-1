#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

/*
	숫자 카드를 상근이가 가지고 있는지 아닌지
	N ~ 50만
*/

int N, M; // 숫자카드 개수
int card[500001];

bool binary_search(int num) {
	int left = 0;
	int right = N - 1;

	while (left <= right) {
		int mid = (left + right) / 2;

		if (card[mid] == num) return true;
    
		if (card[mid] < num) left = mid + 1;
		else right = mid - 1;
	}
	return false;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> card[i];
	}
	sort(card, card + N);

	cin >> M;
	while (M-- > 0) {
		int num;
		cin >> num;
		bool flag = binary_search(num);
		cout << (flag ? 1 : 0) << " ";
	}

	return 0;
}
