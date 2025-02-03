import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {
        int number, idx;

        public Node(int number, int idx) {
            this.number = number;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return o.number - number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        List<Integer> list1 = new ArrayList<>();
        List<Node> list2 = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list1.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            list2.add(new Node(number, i));
        }

        Collections.sort(list1);
        Collections.sort(list2);
        for (int i = 0; i < N; i++) {
            answer += list2.get(i).number * list1.get(i);
        }

        System.out.println(answer);
    }
}