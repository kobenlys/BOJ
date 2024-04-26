import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static ArrayList<ArrayList<node>> coast = new ArrayList<>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void preprocess() {
        Queue<node> qu = new LinkedList<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int cnt = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!vi[i][j] && arr1[i][j] == 1) {
                    coast.add(new ArrayList<>());
                    qu.offer(new node(j, i));
                    vi[i][j] = true;
                    cnt++;

                    while (!qu.isEmpty()) {

                        node nd = qu.poll();
                        boolean isCheck = false;

                        for (int k = 0; k < 4; k++) {
                            int nx = nd.x + dx[k];
                            int ny = nd.y + dy[k];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;


                            if (arr1[ny][nx] == 1 && !vi[ny][nx]) {
                                vi[ny][nx] = true;
                                qu.offer(new node(nx, ny));
                            } else if (arr1[ny][nx] == 0 && !isCheck) {
                                isCheck = true;
                                coast.get(cnt).add(new node(nd.x, nd.y));
                            }
                        }
                    }
                }
            }
        }
    }

    public static int findBridge(node nd, int idx) {
        int res = Integer.MAX_VALUE;

        for (int i = idx; i < coast.size(); i++) {
            for (int j = 0; j < coast.get(i).size(); j++) {
                node now = coast.get(i).get(j);
                int tmp = Math.abs(nd.x - now.x) + Math.abs(nd.y - now.y);
                res = Math.min(res, tmp - 1);
            }
        }


        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int answer = Integer.MAX_VALUE;
        arr1 = new int[N][N];
        vi = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr1[i][j] = n;
            }
        }

        preprocess();

        for (int i = 0; i < coast.size() - 1; i++) {
            for (int j = 0; j < coast.get(i).size(); j++) {
                node nd = coast.get(i).get(j);
                answer = Math.min(answer, findBridge(nd, i + 1));
            }
        }

        System.out.println(answer);
    }
}