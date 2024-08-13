import java.io.*;
import java.util.*;

public class Solution {

    public static class node implements Comparable<node> {
        int x, y, num, lifeTime, status;

        public node(int x, int y, int num, int lifeTime, int status) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.lifeTime = lifeTime;
            this.status = status;
        }

        @Override
        public int compareTo(node o) {
            return o.num - num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            boolean[][] vi = new boolean[N + K * 2][M * K * 2];
            List<node> list = new ArrayList<>();
            PriorityQueue<node> pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num > 0) {
                        list.add(new node(j + K, i + K, num, num, 1));
                        vi[i + K][j + K] = true;
                    }
                }
            }

            int time = 1;


            while (time <= K) {


                while (!pq.isEmpty()) {
                    node tmp = pq.poll();
                    list.add(tmp);
                }

                for (int i = 0; i < list.size(); i++) {

                    node tmp = list.get(i);

                    if (tmp.status == 1 && time == tmp.lifeTime) {

                        list.get(i).status = 2;
                        list.get(i).lifeTime = time + tmp.num;

                        for (int j = 0; j < 4; j++) {
                            int nx = tmp.x + dx[j];
                            int ny = tmp.y + dy[j];

                            if (!vi[ny][nx]) {
                                vi[ny][nx] = true;
                                pq.offer(new node(nx, ny, tmp.num, tmp.lifeTime + 1, 1));
                            }
                        }
                    } else if (tmp.status == 2 && time == tmp.lifeTime) {
                        list.remove(i);
                        i--;
                    }
                }

                time++;
            }


            sb.append("#").append(tc).append(" ");
            sb.append(list.size()).append("\n");
        }

        System.out.println(sb);
    }
}