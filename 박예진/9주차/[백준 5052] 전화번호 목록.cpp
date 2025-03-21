#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

struct Trie {
	bool finish;
	Trie *node[10];

	Trie() {
		finish = false;
		for (int i = 0; i < 10; i++) {
			node[i] = nullptr;
		}
	}

	// 문자열 삽입
	void insert(const char *str) {
		if (*str == '\0') {
			finish = true;
			return;
		}

		int now = *str - '0';
		if (node[now] == nullptr) node[now] = new Trie();
		node[now]->insert(str + 1);
	}

	// 문자열 기존 번호 접두어인지 확인
	bool find(const char *str) {
		if (*str == '\0') return false; 
		if (finish) return true; // 접두어

		int now = *str - '0';
		if (node[now] == nullptr) return false;
		return node[now]->find(str + 1);
	}
};

char input[10001][11];
vector<string> v;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);	cout.tie(NULL);

	int tc;
	cin >> tc;
	while (tc-- > 0) {
		int n;
		cin >> n;

		Trie *root = new Trie();
		for (int i = 0; i < n; i++) {
			cin >> input[i];
			v.push_back(input[i]);
			root->insert(input[i]);
		}
		for (int i = 0; i < n; i++) {
			if (root->find(input[i])) {
				cout << "NO\n";
				break;
			}
			else if (i == n - 1) {
				cout << "YES\n";
			}
		}
	}

	return 0;
}
