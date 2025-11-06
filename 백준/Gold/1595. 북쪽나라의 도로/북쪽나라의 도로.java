import java.io.*;
import java.util.*;

public class Main {

    public static List<List<Node>> list;
    public static boolean[] vi;
    public static int startNode, max;

    public static class Node {

        int goal, value;

        public Node(int goal, int value) {
            this.goal = goal;
            this.value = value;
        }
    }

    public static void dfs(int idx, int dist) {
        if (max < dist) {
            max = dist;
            startNode = idx;
        }

        for (Node nd : list.get(idx)) {

            if (!vi[nd.goal]) {
                vi[nd.goal] = true;
                dfs(nd.goal, dist + nd.value);
                vi[nd.goal] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        list = new ArrayList<>();
        vi = new boolean[10001];
        int answer = 0;
        String tmp = "";

        for (int i = 0; i <= 10001; i++) {
            list.add(new ArrayList<>());
        }

        while (true) {

            tmp = br.readLine();
            if (tmp == null || tmp.isEmpty()) {
                break;
            }

            st = new StringTokenizer(tmp);
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, v));
            list.get(e).add(new Node(s, v));
        }

        vi[1] = true;
        dfs(1, 0);
        max = 0;

        vi = new boolean[10000];
        vi[startNode] = true;
        dfs(startNode, 0);
        System.out.println(max);
    }
}