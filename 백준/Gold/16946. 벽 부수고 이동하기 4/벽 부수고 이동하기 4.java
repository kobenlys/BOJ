import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1; // 맵
    public static int[][] vi; // 방문 처리 + 공백체크
    public static int[][] union; // 같은 집합인지 표시
    
    // 좌표 객체
    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // 공백 전처리 -> 공백 카운트 하기.
    public static void preProcess(int x, int y, int flag) {
        Queue<node> qu = new LinkedList<>();
        Stack<node> stk = new Stack<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        qu.offer(new node(x, y));
        stk.push(new node(x, y));
        vi[y][x] = -1;
        int cnt = 1;

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (arr1[ny][nx] == 0 && vi[ny][nx] == 0) {
                    cnt++; // 이동 가능한 공백 카운트
                    vi[ny][nx] = -1; // 일단 방문처리.
                    stk.push(new node(nx, ny)); // 이동한 칸 좌표 입력
                    qu.offer(new node(nx, ny)); // 큐에 다음 이동넣기.
                }
            }
        }

        for (node nd : stk) {
            vi[nd.y][nd.x] = cnt; // 이동한 칸 마다 총 공백수 넣기.
            union[nd.y][nd.x] = flag; // 같은 집합 표시.
        }
    }
    
    // 이동 할 수 있는 칸 체크
    public static int cntSpace(int x, int y) {
        HashSet<Integer> set = new HashSet<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int ans = 1;
        // vi배열 상하좌우 숫자 ans에 더하기.
        for (int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위에 맞지 안으면 continue + 같은 집합에 속한 공백은 다시 카운트 x
            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if (set.contains(union[ny][nx]) || union[ny][nx] == 0) continue;

            set.add(union[ny][nx]);
            ans += vi[ny][nx];
        }
        // 10으로 나눈 수 출력해야함.
        return ans % 10;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        vi = new int[N][M];
        union = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }

        int cnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 0 && vi[i][j] == 0) {
                    preProcess(j, i, cnt++); // 전처리 + 같은 집합 표시
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 1) {
                    sb.append(cntSpace(j, i));
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}