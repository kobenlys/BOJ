import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {

        int number, val;

        public Node(int number, int val) {
            this.number = number;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            if (number == o.number) {
                return o.val - val;
            }
            return o.number - number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int score = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int gradeCount = 0;
        int showCount = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        List<Node> list = new ArrayList<>();

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(new Node(Integer.parseInt(st.nextToken()), 0));
            }
        }

        list.add(new Node(score, -1));
        Collections.sort(list);
        int answer = -1;
        int grade = 1;
        for (int i = 0; i < Math.min(list.size(), P); i++) {

            Node cd = list.get(i);

            if (cd.val == -1) {
                answer = grade;
                break;
            }

            if (i < Math.min(list.size(), P) - 1) {
                Node nd = list.get(i + 1);
                if (cd.number != nd.number) {
                    grade = i+2;
                }
            }else{
                grade = i+2;
            }
        }


        System.out.println(answer);

    }
}