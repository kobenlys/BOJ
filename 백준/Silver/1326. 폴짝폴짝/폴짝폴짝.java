import java.io.*;
import java.util.*;

public class Main {

    public static int[] bfs(int start, int[] arr1) {
        int[] vi = new int[arr1.length];
        Arrays.fill(vi, Integer.MAX_VALUE);
        Queue<Integer> qu = new ArrayDeque<>();
        vi[start] = 0;
        qu.offer(start);
        int count = 1;

        while (!qu.isEmpty()) {

            int size = qu.size();

            for (int ii = 0; ii < size; ii++) {
                int idx = qu.poll();
                int tmp = arr1[idx];
                for (int i = idx; i < arr1.length; i += tmp) {
                    if (vi[i] > count) {
                        vi[i] = count;
                        qu.offer(i);
                    }
                }

                for (int i = idx; i > 0; i -= tmp) {
                    if (vi[i] > count) {
                        vi[i] = count;
                        qu.offer(i);
                    }
                }
            }
            count++;
        }
        return vi;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[] dist = bfs(s, arr1);
        System.out.println(dist[e] == Integer.MAX_VALUE ? -1 : dist[e]);

    }
}
