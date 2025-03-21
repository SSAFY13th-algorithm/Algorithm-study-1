#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
using namespace std;

/*
	1. 임의 한 위부터 k개의 접시를 연속해서 먹을 경우, 할인된 정액 가격 제공
	2. 각 고객에게 초밥 종류 하나 쓰인 쿠폰 발행, 1번 행사에 참가할 경우
	   쿠폰에 적혀진 종류 초밥 하나 무료 제공
	   없을 경우 요리사 새로 만들어서 제공

	초밥 가짓수의 최댓값
	슬라이딩 윈도우
	원형
*/

int N, d, k, c; // 접시, 초밥가짓수, 연속접시수, 쿠폰번호
int sushi[30001];
int answer; // 최대 가짓수

void solution() {
	// 슬라이딩 윈도우
	int left = 0;
	int right = left + k - 1;

	map <int, int> kind; // 초밥 종류
	for (int i = left; i <= right; i++) {
		kind[sushi[i]]++;
	}

	answer = max(answer, kind.find(c) == kind.end() ? (int)kind.size() + 1: (int)kind.size());

	// 슬라이딩 윈도우 이동
	for (int left = 1; left < N; left++) {
		int right = (left + k - 1) % N; // 원형 처리

		// 왼쪽 
		kind[sushi[left - 1]]--;
		if (kind[sushi[left - 1]] == 0) kind.erase(sushi[left - 1]);

		// 오른쪽
		kind[sushi[right]]++;

		answer = max(answer, kind.find(c) == kind.end() ? (int)kind.size() + 1 : (int)kind.size());
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> d >> k >> c;
	for (int i = 0; i < N; i++) {
		cin >> sushi[i];
	}
	solution();

	cout << answer;

	return 0;
}
