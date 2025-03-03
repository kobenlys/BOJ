import java.io.*;
import java.util.*;

public class Main {
    public static Node root = new Node();
    public static char[] arr1 = {'A', 'C', 'G', 'T'};
    public static String answer = "";

    public static class Node {
        Map<Character, Node> map = new HashMap<>();
    }

    public static void insertTrie(String str) {
        Node parent = root;
        for (int i = 0; i < str.length(); i++) {
            char target = str.charAt(i);
            parent = parent.map.computeIfAbsent(target, e -> new Node());
        }
    }

    public static void findDna(Node parent, StringBuilder sb) {

        if (parent.map.size() < 4) {
            if (answer.length() > sb.toString().length() + 1 || answer.isEmpty()) {
                for (int i = 0; i < 4; i++) {
                    if (!parent.map.containsKey(arr1[i])) {
                        answer = sb.toString() + arr1[i];
                    }
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            String targetChar = String.valueOf(arr1[i]);
            StringBuilder next = new StringBuilder(sb.toString() + targetChar);
            findDna(parent.map.get(arr1[i]), next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        sb = new StringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            String insertStr = sb.substring(i, str.length());
            insertTrie(insertStr);
        }

        for (int i = 0; i < 4; i++) {
            if (root.map.containsKey(arr1[i])) {
                findDna(root.map.get(arr1[i]), new StringBuilder(String.valueOf(arr1[i])));
            } else {
                System.out.println(arr1[i]);
                return;
            }
        }
        System.out.println(answer);
    }
}