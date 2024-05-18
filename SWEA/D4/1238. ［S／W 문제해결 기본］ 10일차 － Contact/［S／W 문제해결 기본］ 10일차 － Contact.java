import java.io.*;
import java.util.*;

public class Solution {
    public static int depth, answer;
    public static ArrayList<ArrayList<Integer>> arr1;
    public static boolean[] vi;

    public static class node {
        int to, step;

        public node(int to, int step) {
            this.to = to;
            this.step = step;
        }
    }

    public static void bfs(int node) {
        Queue<node> qu = new LinkedList<>();
        qu.offer(new node(node, 1));
		// 인접리스트 그래프 탐색
        while (!qu.isEmpty()) {
            node nd = qu.poll();
			// 가장 멀리 떨어진 노드 중 가장 큰 노드 찾기
            if (depth <= nd.step) {
                if (depth < nd.step) {
                    depth = nd.step;
                    answer = nd.to;
                }
                answer = Math.max(answer, nd.to);
            }

            for (int i = 0; i < arr1.get(nd.to).size(); i++) {
                int to = arr1.get(nd.to).get(i);
                if (!vi[to]) {
                    vi[to] = true;
                    qu.offer(new node(to, nd.step+1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        for (int tc = 1; tc <= 10; tc++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            depth = answer = 0;
            arr1 = new ArrayList<>();
            vi = new boolean[101];
            for (int i = 0; i < 101; i++) {
                arr1.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N/2; i++) {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                arr1.get(s).add(e);
            }

            vi[S] = true;
            bfs(S);
            sb.append("#").append(tc).append(" ");
            sb.append(answer);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}