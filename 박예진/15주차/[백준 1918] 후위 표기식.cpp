#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <stack>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	string str, res = "";
	cin >> str;

	stack <char> s;
	for (int i = 0; i < str.size(); i++) {
		if ('A' <= str[i] && str[i] <= 'Z') {
			res += str[i];
		}
		else {
			switch (str[i]) {
			case '(':
				s.push(str[i]);
				break;
			case '*':
			case '/':
				while (!s.empty() && (s.top() == '*' || s.top() == '/')) {
					res += s.top();
					s.pop();
				}
				s.push(str[i]);
				break;
			case '+':
			case '-':
				while (!s.empty() && s.top() != '(') {
					res += s.top();
					s.pop();
				}
				s.push(str[i]);
				break;
			case ')':
				while (!s.empty() && s.top() != '(') {
					res += s.top();
					s.pop();
				}
				s.pop();
				break;
			}
		}
	}
	while (!s.empty()) {
		res += s.top();
		s.pop();
	}

	cout << res;

	return 0;
}
