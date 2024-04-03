import java.io.*;
import java.util.*;

public class Main {
    public static int R, C;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static ArrayList<node> swan = new ArrayList<>();
    public static Queue<node> nowSwan, water;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 물과 인접한 얼음 녹이기.
    public static void iceMelt() {

        int size = water.size();
        // 큐에 입력할때, 이번턴에 입력된 데이터 사용방지.
        for (int i = 0; i < size; i++) {
            // 물 위치
            node nd = water.poll();

            for (int j = 0; j < 4; j++) {
                int nx = nd.x + dx[j];
                int ny = nd.y + dy[j];

                if (nx < 0 || ny < 0 || nx >= C || ny >= R) continue;
                // 물과 인접한 얼음 발견시
                if (arr1[ny][nx] == 'X') {
                    // 다음턴에 사용될 물 위치 저장, 맵 업데이트
                    water.offer(new node(nx, ny));
                    arr1[ny][nx] = '.';
                }
            }
        }
    }

    // 백조 찾기.
    public static boolean findSwan() {
        Queue<node> qu = new LinkedList<>();

        while (!nowSwan.isEmpty()) {

            node nd = nowSwan.poll(); // 이전 턴 백조 위치.

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= C || ny >= R) continue;

                if (!vi[ny][nx]) {

                    if (swan.get(1).x == nx && swan.get(1).y == ny) return false;
                    vi[ny][nx] = true;

                    // 백조 이동
                    if (arr1[ny][nx] == '.') {
                        nowSwan.offer(new node(nx, ny));
                    } else if (arr1[ny][nx] == 'X') {
                        // 얼음을 만났다면 다음턴 백조 시작하는 자리임. -> qu에 저장한다.
                        qu.offer(new node(nx, ny));
                    }
                }
            }
        }
        // 시간초과 방지위해 이번턴에 이동했던 백조위치를 저장한다.
        nowSwan = qu;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        nowSwan = new LinkedList<>();
        water = new LinkedList<>();
        vi = new boolean[R][C];
        arr1 = new char[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                char n = input.charAt(j);
                arr1[i][j] = n;

                if (n == 'L') {
                    swan.add(new node(j, i));
                    water.offer(new node(j, i)); // 당연히 물위에 백조가 있다..
                    arr1[i][j] = '.';
                } else if (n == '.') {
                    water.offer(new node(j, i));
                }
            }
        }

        int cnt = 0;
        nowSwan.add(new node(swan.get(0).x, swan.get(0).y)); // 처음 백조위치
        // findSwan() 함수가 false를 리턴시 루프 종료.
        while (findSwan()) {
            iceMelt();
            cnt++;
        }
        System.out.println(cnt);
    }
}
