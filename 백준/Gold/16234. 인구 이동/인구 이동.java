import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, L, R;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static boolean isUpdate = false;
    public static Queue<node> qu = new LinkedList<>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int x, int y) {
        Stack<node> stk = new Stack<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        stk.push(new node(x, y));
        qu.offer(new node(x, y));
        int max = arr1[y][x];
        int cnt = 1;

        while (!qu.isEmpty()) {
            node nd = qu.poll();
            int nowPeople = arr1[nd.y][nd.x];
            // bfs로 조건에 맞는 개방가능 한 주변국가 최대한 탐색 한다.
            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (!vi[ny][nx]) {
                    int newPeople = Math.abs(nowPeople - arr1[ny][nx]);
                    // 두 국가의 국민 수 차이가 L 이상 R 이하 인 경우
                    if (newPeople >= L && newPeople <= R) {
                        max += arr1[ny][nx];
                        cnt++;
                        isUpdate = true; // 업데이트 표시.
                        vi[ny][nx] = true; // 하루 중 방문했던 국가는 더이상 방문 불가.
                        qu.offer(new node(nx, ny));
                        stk.push(new node(nx, ny)); // 개방된 국가 목록 저장
                    }
                }
            }
        }
        // 개방된 국가가 없다면 패스한다.
        if (stk.size() > 1) {
            isUpdate = true;
            // 개방된 국가 목록, 한칸당 인구 매개변수로 함수 호출
            movePeople(stk, max / cnt);
        }
    }

    // 고르게 인구 분포, 스택에 담아둔 국가에 입력한다.
    public static void movePeople(Stack<node> stk, int p) {
        while (!stk.isEmpty()) {
            node nd = stk.pop();
            arr1[nd.y][nd.x] = p;
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            isUpdate = false;
            vi = new boolean[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!vi[i][j]) { // 방문하지 않은 국가들 탐색
                        vi[i][j] = true;
                        bfs(j, i); // 너비우선탐색알고리즘
                    }
                }
            }
            // 모든 탐색후 업데이트가 되었다면 day++
            if (isUpdate) {
                day++;
            } else {
                // 더이상 업데이트 불가시 break;
                break;
            }
        }
        System.out.print(day);
    }
}