import java.io.*;
import java.util.*;

class Solution {
    public static int N, M;
    public static int[] ans;
    public static int[][] lands;
    public static boolean[][] vi;

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int x, int y) {
        HashSet<Integer> set = new HashSet<>();
        Queue<node> qu = new LinkedList<>();
        qu.offer(new node(x, y));
        set.add(x);
        int cnt = 0;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (!vi[ny][nx] && lands[ny][nx] == 1) {
                    cnt++;
                    vi[ny][nx] = true;
                    if (!set.contains(nx)) {
                        set.add(nx);
                    }
                    qu.offer(new node(nx, ny));
                }
            }
        }
        if(cnt == 0) cnt = 1;
        for (int s : set) {
            ans[s] += cnt;
        }
    }
    
    public int solution(int[][] land) {
        
        lands = land;

        N = land.length;
        M = land[0].length;
        ans = new int[M];
        vi = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!vi[i][j] && land[i][j] == 1) {
                    bfs(j, i);
                }
            }
        }

        Arrays.sort(ans);
        int answer = ans[M-1];
        return answer;
    }
}