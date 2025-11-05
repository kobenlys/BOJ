import java.io.*;
import java.util.*;

public class Main {

    public static String strA, strB, tget;

    public static class Node {

        int idxA, idxB, idxT;

        public Node(int idxA, int idxB, int idxT) {
            this.idxA = idxA;
            this.idxB = idxB;
            this.idxT = idxT;
        }
    }

    public static boolean bfs() {
        Queue<Node> qu = new ArrayDeque<>();
        boolean[][] vi = new boolean[strA.length() + 1][strB.length() + 1];

        if (tget.charAt(0) == strA.charAt(0)) {
            vi[1][0] = true;
            qu.offer(new Node(1, 0, 1));
        }
        if (tget.charAt(0) == strB.charAt(0)) {
            vi[0][1] = true;
            qu.offer(new Node(0, 1, 1));
        }

        while (!qu.isEmpty()) {

            Node nd = qu.poll();

            if (nd.idxT == tget.length()) {
                return true;
            }

            if (strA.length() > nd.idxA && !vi[nd.idxA + 1][nd.idxB]
                && tget.charAt(nd.idxT) == strA.charAt(nd.idxA)) {
                vi[nd.idxA + 1][nd.idxB] = true;
                qu.offer(new Node(nd.idxA + 1, nd.idxB, nd.idxT + 1));
            }

            if (strB.length() > nd.idxB && !vi[nd.idxA][nd.idxB + 1]
                && tget.charAt(nd.idxT) == strB.charAt(nd.idxB)) {
                vi[nd.idxA][nd.idxB + 1] = true;
                qu.offer(new Node(nd.idxA, nd.idxB + 1, nd.idxT + 1));
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int cnt = 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            strA = st.nextToken();
            strB = st.nextToken();
            tget = st.nextToken();

            sb.append("Data set ").append(i + 1).append(": ");

            if (bfs()) {
                sb.append("yes");
            } else {
                sb.append("no");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}