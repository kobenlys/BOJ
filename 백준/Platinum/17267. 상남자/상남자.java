import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;
    public static int[][] vi;
    public static Queue<node> qu = new LinkedList<>();
    
    // 좌표, 좌 우 이동범위 담는 객체
    public static class node {
        int x, y, left, right;

        public node(int x, int y, int left, int right) {
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }
    }

    public static void bfs() {

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        // 초기 방문처리.
        vi[qu.peek().y][qu.peek().x] = qu.peek().left + qu.peek().right;

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                // 현재 가능한 좌우 이동범위 합
                int sumVi = nd.left + nd.right;

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                // 중복 루트여도, 현재 좌, 우로 갈수있는 기회가 더 많다면 중복 허용.
                // 히든테케 제거하기 위한 방문처리임
                if (vi[ny][nx] < sumVi && arr1[ny][nx] == 0) {

                    if (i < 2) {
                        // 상, 하 무제한 이동
                        vi[ny][nx] = sumVi;
                        qu.offer(new node(nx, ny, nd.left, nd.right));
                    } else {
                        // 좌, 우 이동할 수 있는지 판단.
                        if (i == 2 && nd.left > 0) {
                            vi[ny][nx] = sumVi - 1;
                            qu.offer(new node(nx, ny, nd.left - 1, nd.right));
                            
                        } else if (i == 3 && nd.right > 0) {
                            vi[ny][nx] = sumVi - 1;
                            qu.offer(new node(nx, ny, nd.left, nd.right - 1));
                            
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        vi = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int answer = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Character.getNumericValue(str.charAt(j));
                vi[i][j] = -1;
                if (arr1[i][j] == 2) {
                    arr1[i][j] = 0;
                    qu.offer(new node(j, i, l, r));
                }
            }
        }
        // 너비우선탐색 실행.
        bfs();
        // 방문한 곳 카운트
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (vi[i][j] != -1) answer++;
            }
        }

        System.out.print(answer);
    }
}