import java.io.*;
import java.util.*;

public class Main {

    public static class Trie{
        public Node root = new Node();
        public int result = 0;

        public static class Node{
            Map<Character, Node> map = new HashMap<>();
            boolean isBlock = false;
            boolean isEnd = false;
        }

        public void insertFile(String str, int controller) {
            Node parent = root;

            if (controller == 1 && !parent.map.containsKey(str.charAt(0))) {
                return;
            }

            for (int i = 0; i < str.length(); i++) {
                parent = parent.map.computeIfAbsent(str.charAt(i), e -> new Node());
                if (controller == 1) {
                    parent.isBlock = true;
                }
            }
            if(controller == 0){
                parent.isEnd = true;
            } else{
                parent.isEnd = false;
            }
        }

        public void getMinDeleteCnt(Node parent) {
            if(parent.isEnd && parent.isBlock) result++;

            for (Map.Entry<Character, Node> object : parent.map.entrySet()) {
                Character key = object.getKey();
                Node next = parent.map.get(key);
                if(!next.isBlock){
                    result++;
                    continue;
                }
                getMinDeleteCnt(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            Trie trie = new Trie();

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                String file = br.readLine();
                trie.insertFile(file, 0);
            }

            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                String blockFile = br.readLine();
                trie.insertFile(blockFile, 1);
            }

            if (N == 0) {
                sb.append(1).append("\n");
            }else{
                trie.getMinDeleteCnt(trie.root);
                sb.append(trie.result).append("\n");
            }
        }
        System.out.println(sb);
    }
}