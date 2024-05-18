import java.io.*;
import java.util.*;

public class Solution {
    public static ArrayList<ArrayList<Integer>> arr1;

    public static boolean dijkstra(int node) {
        Queue<Integer> qu = new LinkedList<>();
        boolean[] dist = new boolean[100];
        dist[node] = true;
        qu.offer(node);

        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int i = 0; i < arr1.get(now).size(); i++) {
                int tmp = arr1.get(now).get(i);
                if (!dist[tmp]) {
                    dist[tmp] = true;
                    qu.offer(tmp);
                }
            }
        }
        return dist[99];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            arr1 = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                arr1.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                arr1.get(s).add(e);
            }

            sb.append("#").append(tc).append(" ");
            sb.append(dijkstra(0) ? 1 : 0);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}