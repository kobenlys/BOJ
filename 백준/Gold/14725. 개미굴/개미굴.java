import java.io.*;
import java.util.*;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static Node root = new Node();

    public static class Node {
        Map<String, Node> map = new HashMap<>();
    }

    public static void printTrie(Node parent, int depth) {
        List<String> list = new ArrayList<>(parent.map.keySet());
        Collections.sort(list);


        for (String str : list) {
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(str).append("\n");
            printTrie(parent.map.get(str), depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());

            Node parent = root;

            for (int j = 0; j < t; j++) {
                String str = st.nextToken();
                parent = parent.map.computeIfAbsent(str, e -> new Node());
            }
        }

        printTrie(root, 0);
        System.out.println(sb);
    }
}