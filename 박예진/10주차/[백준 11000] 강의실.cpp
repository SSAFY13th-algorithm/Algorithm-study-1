#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

/*
	최소의 강의실을 사용해 모든 수업 가능하도록
*/

struct Lecture {
	int start, end;

	bool operator() (Lecture l1, Lecture l2) {
		return l1.start < l2.start;
	}
};

int N;
vector<Lecture> lectures;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N;
	lectures.resize(N);
	for (int i = 0; i < N; i++) {
		cin >> lectures[i].start >> lectures[i].end;
	}

	sort(lectures.begin(), lectures.end(), Lecture());

	priority_queue<int, vector<int>, greater<int>> pq; // 강의실 개수
	pq.push(lectures[0].end);

	for (int i = 1; i < N; i++) {
		if (lectures[i].start >= pq.top()) pq.pop();
		pq.push(lectures[i].end);
	}

	cout << pq.size();

	return 0;
}
