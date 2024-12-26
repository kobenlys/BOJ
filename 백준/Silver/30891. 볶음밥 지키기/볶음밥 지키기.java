import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Node answer = new Node(0, 0);
        int tmpMax = 0;
        List<Node> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Node(x, y));
        }

        for (int i = -100; i <= 100; i++) {
            for (int j = -100; j <= 100; j++) {

                int cnt = 0;

                for (Node nd : list) {
                    double dist = Math.sqrt(Math.pow(j - nd.x, 2) + Math.pow(i - nd.y, 2));
                    if (dist <= M) {
                        cnt++;
                    }
                }

                if (tmpMax < cnt) {
                    tmpMax = cnt;
                    answer.y = i;
                    answer.x = j;
                }
            }
        }
        System.out.println(answer.x + " " + answer.y);
    }
}