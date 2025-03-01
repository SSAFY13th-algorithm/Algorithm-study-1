#include <iostream>
#include <algorithm>
#include <map>
#include <string>
using namespace std;

/*
	최대 N개의 종류의 알파벳을 가진 연속된 문자열
	최대 문자열 길이
*/

int N;
string str;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> str;

	int left = 0;
	int right = 0;
	int answer = 0;

	map <char, int> m; // 알파벳 개수
	m[str[0]] = 1;

	while (right < str.size()) {
		// 문자열 최대길이 업데이트
		if (m.size() <= N) {
			cout << left << " " << right << "\n";
			answer = max(answer, right - left + 1);
		}

		right++;
		m[str[right]]++;

		while(m.size() > N) {
			if (m[str[left]] == 1) {
				m.erase(str[left]);
			}
			else m[str[left]]--;
			
			left++;
		}
	}

	cout << answer;

	return 0;
}
