import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static ArrayList<node> copy;
    public static Queue<node> qu = new LinkedList<>();
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static class node {
        int x, y, cnt;

        public node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    // 불 퍼트리기.
    public static void moveFire() {
        ArrayList<node> tmp = new ArrayList<>();

        for (int i = 0; i < copy.size(); i++) {
            node fire = copy.get(i);
            for (int j = 0; j < 4; j++) {
                int nx = fire.x + dx[j];
                int ny = fire.y + dy[j];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (arr1[ny][nx] == '.') {
                    arr1[ny][nx] = 'F';
                    tmp.add(new node(nx, ny, 0)); // 새로운 불 위치 저장
                }
            }
        }
        // 다음 턴에 사용하기위해 copy에 복사하기.
        copy = new ArrayList<>(tmp);
    }
    
    // 사람 이동하기.
    public static void bfs() {
        int flag = 0;

        while (!qu.isEmpty()) {

            node nd = qu.poll();
            // 사람이동 , 불 퍼짐 속도 맞추기. -> nd.cnt가 변경될때 moveFire함수 실행.
            if (nd.cnt != flag) {
                moveFire();
                flag = nd.cnt;
            }
            // 내가 지금 있는곳이 불탄다면 -> 탈출 불가.
            if (arr1[nd.y][nd.x] == 'F') continue;

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                // 가장 먼저 탈출 -> 최소값임.
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
                    System.out.println(nd.cnt + 1);
                    System.exit(0);
                }
                // 불이 없는 길이라면 이동
                if (!vi[ny][nx] && arr1[ny][nx] == '.') {
                    vi[ny][nx] = true;
                    qu.offer(new node(nx, ny, nd.cnt + 1));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new char[N][M];
        vi = new boolean[N][M];
        copy = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);

                if (arr1[i][j] == 'J') {
                    qu.offer(new node(j, i, 0));
                    vi[i][j] = true;
                    arr1[i][j] = '.';

                } else if (arr1[i][j] == 'F') {
                    copy.add(new node(j, i, 0));
                }
            }
        }
        // BFS 함수 실행
        bfs();
        System.out.println("IMPOSSIBLE");
    }
}