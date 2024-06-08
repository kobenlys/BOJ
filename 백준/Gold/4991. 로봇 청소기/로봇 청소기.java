import java.io.*;
import java.util.*;

public class Main {
    public static int W, H, cnt, answer;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static boolean[] dfsVi;
    public static ArrayList<ArrayList<trace>> list;

    public static class trace {
        int goal, val;

        public trace(int goal, int val) {
            this.goal = goal;
            this.val = val;
        }
    }

    public static class node {
        int x, y, step;

        public node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    
    // 각 노드 방문 순서별 거리 구하기.
    public static void dfs(int start, int dist, int depth) {

        if (depth == cnt) {
            // 가장 작은 값 구하기
            answer = Math.min(answer, dist);
            return;
        }

        for (int i = 0; i < list.get(start).size(); i++) {
            trace tr = list.get(start).get(i);
            if (!dfsVi[tr.goal]) {
                dfsVi[tr.goal] = true;
                dfs(tr.goal, dist + tr.val, depth + 1);
                dfsVi[tr.goal] = false;
            }
        }
    }
    
    // 노드별 최단거리 구하기
    public static int bfs(int sX, int sY, int eX, int eY) {
        Queue<node> qu = new LinkedList<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        qu.offer(new node(sX, sY, 0));

        int res = -1;

        while (!qu.isEmpty()) {

            node nd = qu.poll();
            // BFS S -> E 최단거리 구하기.
            if (nd.x == eX && nd.y == eY) {
                res = nd.step;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;

                if (!vi[ny][nx]) {
                    if (arr1[ny][nx] != 'x') {
                        vi[ny][nx] = true;
                        qu.offer(new node(nx, ny, nd.step + 1));
                    }
                }
            }
        }

        return res;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {

            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            
            arr1 = new char[H][W];
            dfsVi = new boolean[11];
            int[][] target = new int[11][2];
            list = new ArrayList<>();

            if (W == H && W == 0) break;

            cnt = 0;
            answer = Integer.MAX_VALUE;

            for (int i = 0; i <= 10; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < H; i++) {
                String input = br.readLine();
                for (int j = 0; j < W; j++) {
                    arr1[i][j] = input.charAt(j);
                    // 노드별 좌표 저장.
                    if (arr1[i][j] == 'o') {
                        target[0][0] = j;
                        target[0][1] = i;
                    }

                    if (arr1[i][j] == '*') {
                        target[cnt + 1][0] = j;
                        target[++cnt][1] = i;
                    }
                }
            }

            boolean isPossible = true;

            st:
            for (int i = 0; i <= cnt; i++) {
                for (int j = i + 1; j <= cnt; j++) {
                    vi = new boolean[H][W];
                    // 시작노드 -> 목표노드 최단거리 구하기.
                    int step = bfs(target[i][0], target[i][1], target[j][0], target[j][1]);
                    if (step == -1) {
                        // 하나라도 이어져있지 않다면 청소 불가.
                        sb.append(-1).append("\n");
                        isPossible = false;
                        break st;
                    }
                    // 양방향 간선 처리
                    list.get(i).add(new trace(j, step));
                    list.get(j).add(new trace(i, step));
                }
            }

            if (!isPossible) continue;
            dfsVi[0] = true;
            dfs(0, 0, 0); // DFS탐색 + 출력
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}