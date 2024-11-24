import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        String name;
        int score;

        public Node(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Node o) {
            if (o.score == score) {
                return name.compareTo(o.name);
            }
            return o.score - score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        int A = 0;
        int B = 0;
        int G = 0;

        String str1 = "ABC";
        String str2 = "BABC";
        String str3 = "CCAABB";

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == str1.charAt(i % str1.length())) {
                A++;
            }
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == str2.charAt(i % str2.length())) {
                B++;
            }
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == str3.charAt(i % str3.length())) {
                G++;
            }
        }

        List<Node> list = new ArrayList<>();
        list.add(new Node("Adrian", A));
        list.add(new Node("Bruno", B));
        list.add(new Node("Goran", G));
        Collections.sort(list);

        int res = list.get(0).score;
        System.out.println(res);

        for (int i = 0; i < 3; i++) {
            Node nd = list.get(i);
            if(res != nd.score) break;
            System.out.println(nd.name);
            res = nd.score;
        }
    }
}