import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int score1, score2;

        public Node(int score1, int score2) {
            this.score1 = score1;
            this.score2 = score2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int answer = 0;
            List<Node> list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                list.add(new Node(num1, num2));
            }

            list.sort(Comparator.comparingInt(a -> a.score1));
            int minScore = 100_001;

            for (Node nd : list) {
                if (nd.score2 < minScore) {
                    minScore = nd.score2;
                    answer++;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}