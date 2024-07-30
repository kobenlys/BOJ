import java.io.*;
import java.util.*;

public class Main {
    public static int K;
    public static boolean[][] vi;
    public static int[][] time;
    public static Queue<Integer> qu = new LinkedList<>();

    public static void bfs() {
        int[] dx = {2, 1, -1};

        int time = 0;
        while (!qu.isEmpty()) {

            int size = qu.size();

            time++;

            K += time;

            if (K > 500_000) {
                System.out.println(-1);
                System.exit(0);
            }


            for (int i = 0; i < size; i++) {
                int subin = qu.poll();

                for (int j = 0; j < 3; j++) {

                    int nSubin = subin;
                    if (j == 0) {
                        nSubin *= dx[j];
                    } else {
                        nSubin += dx[j];
                    }

                    if (nSubin < 0 || nSubin > 500_000) continue;
                    if (!vi[nSubin][time % 2]) {
                        vi[nSubin][time % 2] = true;
                        qu.offer(nSubin);
                    }
                }
            }
            if(vi[K][time%2]){
                System.out.println(time);
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N == K){
            System.out.println(0);
            System.exit(0);
        }

        vi = new boolean[500_001][2];
        vi[N][0] = true;
        qu.offer(N);
        bfs();
        System.out.println(-1);
    }
}
