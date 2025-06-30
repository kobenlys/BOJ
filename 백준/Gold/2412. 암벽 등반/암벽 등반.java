import java.io.*;
import java.util.*;

public class Main {

    public static int N, T, answer = Integer.MAX_VALUE;
    public static Map<Long, Boolean> map = new HashMap<>();
    public static int[] dx = {-2, -1, 0, 1, 2};
    public static int[] dy = {-2, -1, 0, 1, 2};


    public static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Player {

        long position;
        int step;

        public Player(long position, int step) {
            this.position = position;
            this.step = step;
        }
    }

    public static long convertIndex(int x, int y) {
        return y * 1_000_000L + x;
    }

    public static Node decodeIndex(long index) {
        return new Node((int) (index % 1_000_000), (int) (index / 1_000_000));
    }

    public static void bfs() {

        Queue<Player> qu = new ArrayDeque<>();

        qu.offer(new Player(0L, 0));

        while (!qu.isEmpty()) {

            Player player = qu.poll();
            Node now = decodeIndex(player.position);

            if (now.y == T) {
                answer = Math.min(answer, player.step);
                continue;
            }

            for (int i = 0; i < 5; i++) {
                int ny = now.y + dy[i];
                for (int j = 0; j < 5; j++) {
                    int nx = now.x + dx[j];
                    if (nx == now.x && ny == now.y) {
                        continue;
                    }

                    long next = convertIndex(nx, ny);
                    if (map.containsKey(next) && !map.get(next)) {
                        map.put(next, true);
                        qu.offer(new Player(next, player.step + 1));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map.put(0L, false);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map.put(convertIndex(x, y), false);
        }

        bfs();
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}