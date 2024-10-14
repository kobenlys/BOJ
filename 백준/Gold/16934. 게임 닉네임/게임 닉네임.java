import java.io.*;
import java.util.*;

public class Main {
    public static Node root = new Node();

    public static class Node {
        Map<Character, Node> map = new HashMap<>();
        boolean isEnd;
        int cnt;
    }

    public static void insert(String str) {
        Node parent = root;
        for (int i = 0; i < str.length(); i++) {
            parent = parent.map.computeIfAbsent(str.charAt(i), e -> new Node());
        }
        parent.isEnd = true;
        parent.cnt += 1;
    }

    public static String search(String str) {
        StringBuilder sb = new StringBuilder();
        Node parent = root;

        for (int i = 0; i < str.length(); i++) {
            if(parent == null) return sb.toString();

            sb.append(str.charAt(i));
            parent = parent.map.getOrDefault(str.charAt(i), null);

            if (parent != null) {

                if (str.equals(sb.toString()) && parent.isEnd) {
                    sb.append(parent.cnt + 1);
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            String str = br.readLine();

            sb.append(search(str)).append("\n");
            insert(str);
        }
        System.out.println(sb);
    }
}