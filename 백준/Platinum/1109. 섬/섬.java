import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] dp;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static int[][] islandMap;
    public static List<List<Node>> list = new ArrayList<>();
    public static List<List<Integer>> graph = new ArrayList<>();

    public static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    public static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 1 && y >= 1 && x <= M && y <= N;
    }

    public static void findIsland(int x, int y, int number) {
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(x, y));
        islandMap[y][x] = number;
        list.get(number).add(new Node(x, y));

        while (!qu.isEmpty()) {
            Node nd = qu.poll();

            for (int i = 0; i < 8; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (isRange(nx, ny) && !vi[ny][nx] && arr1[ny][nx] == 'x') {
                    vi[ny][nx] = true;
                    islandMap[ny][nx] = number;
                    list.get(number).add(new Node(nx, ny));
                    qu.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static void fillSpace(int x, int y, int islandIdx) {
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(x, y));
        islandMap[y][x] = -1;
        Set<Integer> set = new HashSet<>();
        boolean isStuck = true;

        while (!qu.isEmpty()) {
            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (isRange(nx, ny)) {
                    if (islandMap[ny][nx] > 0) {
                        set.add(islandMap[ny][nx]);
                        continue;
                    }

                    if (islandMap[ny][nx] == 0) {
                        islandMap[ny][nx] = -1;
                        qu.offer(new Node(nx, ny));
                    }
                } else {
                    isStuck = false;
                }
            }
        }

        if (isStuck) {
            for (int e : set) {
                if(islandIdx == e) continue;
                graph.get(e).add(islandIdx);
            }
        }
    }

    public static void dfs(int idx, int depth) {

        if(dp[idx] > depth) return;
        dp[idx] = depth;

        for (int next : graph.get(idx)) {
            dfs(next, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new char[N + 2][M + 2];
        vi = new boolean[N + 2][M + 2];
        islandMap = new int[N + 2][M + 2];

        list.add(new ArrayList<>());
        graph.add(new ArrayList<>());

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr1[i][j] = str.charAt(j-1);
            }
        }

        int island_Index = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!vi[i][j] && arr1[i][j] == 'x') {
                    list.add(new ArrayList<>());
                    graph.add(new ArrayList<>());
                    findIsland(j, i, island_Index);
                    island_Index++;
                }
            }
        }

        fillSpace(0, 0, 0);

        while (true) {
            boolean isDone = true;
            for (int i = 1; i < list.size(); i++) {
                for (Node pos : list.get(i)) {

                    for (int j = 0; j < 4; j++) {
                        int nx = pos.x + dx[j];
                        int ny = pos.y + dy[j];
                        if (isRange(nx, ny) && islandMap[ny][nx] == 0) {
                            isDone = false;
                            fillSpace(nx, ny, i);
                        }
                    }
                }
            }
            if (isDone) break;
        }

        dp = new int[list.size()];
        Arrays.fill(dp, -1);


        for (int i = 1; i <  dp.length; i++) {
            dfs(i, 1);
        }

        int[] answer = new int[list.size()];
        for (int i = 1; i <  dp.length; i++) {
            if (dp[i] > 0) {
                answer[dp[i]]++;
            }
        }

        for (int i = 1; i < answer.length; i++) {
            if(answer[i] == 0) break;
            sb.append(answer[i]).append(" ");
        }

        if (sb.length() == 0) {
            System.out.println(-1);
        }else{
            System.out.println(sb);
        }
    }
}