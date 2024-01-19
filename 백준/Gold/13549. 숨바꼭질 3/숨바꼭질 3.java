import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, K, min = 100001;
    public static Queue<node> qu = new LinkedList<>();

    public static class node {
        int step, val;

        public node(int step, int val) {
            this.step = step;
            this.val = val;
        }
    }

    public static void bfs(int step, int val) {
        boolean[] vi = new boolean[100001];
        vi[val] = true;
        qu.offer(new node(step, val));

        while (!qu.isEmpty()) {
            node tmp = qu.poll();

            for (int i = 0; i < 3; i++) {
                int nStep = 0;
                int nVal = 0;
                if (i == 0) {
                    nStep = tmp.step;
                    nVal = tmp.val * 2;
                } else if (i == 1) {
                    nStep = tmp.step+1;
                    nVal = tmp.val -1;
                } else {
                    nStep = tmp.step+1;
                    nVal = tmp.val +1;
                }

                if (nVal >= 0 && nVal <= 100000) {
                    if (nVal == K) {
                        min = Math.min(min, nStep);
                    }
                    if (!vi[nVal]) {
                        vi[nVal] = true;
                        qu.offer(new node(nStep, nVal));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (N == K) {
            System.out.print(0);
        } else {
            bfs(0, N);
            System.out.println(min);
        }
    }
}
