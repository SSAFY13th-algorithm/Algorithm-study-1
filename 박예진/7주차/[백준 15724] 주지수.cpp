#include <iostream>
#include <algorithm>
using namespace std;

int N, M, K;
int arr[1025][1025];
int preSum[1025][1025];
int x1, y1, x2, y2;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> N >> M;
    for(int i = 1; i <= N; i++){
      for(int j = 1; j <= M; j++){
        cin >> arr[i][j];
        preSum[i][j] = preSum[i][j - 1] + preSum[i - 1][j] - preSum[i - 1][j - 1] + arr[i][j];
      }
    }
    cin >> K;
    for(int i = 0; i < K; i++){
      cin >> x1 >> y1 >> x2 >> y2;
      cout << preSum[x2][y2] - preSum[x1 - 1][y2] - preSum[x2][y1 - 1] + preSum[x1 - 1][y1 - 1] << "\n";
    }

    return 0;
}
