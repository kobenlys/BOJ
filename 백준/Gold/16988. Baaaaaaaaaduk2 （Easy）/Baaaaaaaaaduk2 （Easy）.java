import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;
    public static List<Node> list = new ArrayList<>();

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(Node nd1, Node nd2) {
        int answer = 0;
        boolean[][] vi = new boolean[N][M];
        Queue<Node> qu = new ArrayDeque<>();
        Node[] tmp = {nd1, nd2};

        vi[nd1.y][nd1.x] = true;
        vi[nd2.y][nd2.x] = true;

        for (int i = 0; i < 2; i++) {
            Node t = tmp[i];

            for (int k = 0; k < 4; k++) {
                int nx = t.x + dx[k];
                int ny = t.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (!vi[ny][nx] && arr1[ny][nx] == 2) {
                    qu.offer(new Node(nx, ny));
                    vi[ny][nx] = true;
                    int cnt = 1;

                    while (!qu.isEmpty()) {

                        Node nd = qu.poll();

                        for (int j = 0; j < 4; j++) {
                            int curX = nd.x + dx[j];
                            int curY = nd.y + dy[j];
                            if (curX < 0 || curY < 0 || curX >= M || curY >= N) continue;

                            if (arr1[curY][curX] == 0 && !vi[curY][curX]) {
                                cnt = 0;
                                qu.clear();
                                break;
                            }

                            if (!vi[curY][curX] && arr1[curY][curX] == 2) {
                                vi[curY][curX] = true;
                                cnt++;
                                qu.offer(new Node(curX, curY));
                            }
                        }
                    }
                    answer += cnt;
                }
            }
        }


        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (arr1[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                        if (arr1[ny][nx] == 2) {
                            list.add(new Node(j, i));
                            break;
                        }
                    }
                }
            }
        }

        int answer = 0;
        if (list.size() == 1) {
            answer = bfs(list.get(0), list.get(0));
        } else {

            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    //System.out.println(bfs(list.get(i), list.get(j)));
                    answer = Math.max(answer, bfs(list.get(i), list.get(j)));
                }
            }

        }


        System.out.println(answer);
    }
}