import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, min = 300, max = 0;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static Queue<node> qu = new LinkedList<>();

    public static class node {
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean bfs(int mid) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int i = min; i + mid <= max; i++) {
            // 최솟값 부터 최솟값+mid값 범위내 값이 배열에 있다면 사용할수있는 경로이다.
            int s = i;
            int e = s + mid;

            if (s > arr1[0][0] || e < arr1[0][0]) continue;

            qu.clear();
            vi = new boolean[N][N];
            qu.offer(new node(0, 0));
            vi[0][0] = true;

            while (!qu.isEmpty()) {

                node nd = qu.poll();
                // 도착 경로 찾았다면 true 반환.
                if (nd.x == N - 1 && nd.y == N - 1) {
                    return true;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = nd.x + dx[j];
                    int ny = nd.y + dy[j];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    if (!vi[ny][nx]) {
                        // s 이상 e 이하인 요소 발견시 탐색진행.
                        if (s <= arr1[ny][nx] && arr1[ny][nx] <= e) {
                            vi[ny][nx] = true;
                            qu.offer(new node(nx, ny));
                        }
                    }
                }
            }
        }
        // 경로를 못찾았다면 false 반환
        return false;
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];

        int ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(arr1[i][j], max); // 배열 내 최댓값
                min = Math.min(arr1[i][j], min); // 배열 내 최솟값
            }
        }
        // 어차피 답이 될 수 있는 최대범위는 최댓값-최솟값임
        // 즉 우린 가장 작은 답을 구해야 하기때문에 이분탐색으로
        // mid값을 줄여나가 문제조건에 부합하는 최솟값을 도출할것임.
        int left = 0;
        int right = max - min;
        // 이분탐색 알고리즘.
        while (left <= right) {

            int mid = (right + left) / 2;
            // bfs 함수 호출
            // mid 값이 정답이라고 가정하고 bfs 실행한다.
            // mid 값에 만족하는 경로가 있는지 판별하기 위해
            if (bfs(mid)) {
                // true 반환시 최솟값이 답이기 때문에 mid값의 크기를 줄인다
                right = mid - 1;
                ans = mid;
            } else {
                // false 반환시 현재 mid값으로 도착경로를 찾을 수 없기때문에 크기를 키워 탐색한다.
                left = mid + 1;
            }
        }
        System.out.print(ans);
    }
}