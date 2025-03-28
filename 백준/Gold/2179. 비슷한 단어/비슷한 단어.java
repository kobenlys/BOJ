import java.io.*;
import java.util.*;

public class Main {
    public static class Node {
        String str;
        int idx;

        public Node(String str, int idx) {
            this.str = str;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Node> list = new ArrayList<>();
        List<String> copy = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            list.add(new Node(br.readLine(), i));
            copy.add(list.get(i - 1).str);
        }

        list.sort(Comparator.comparing(o -> o.str));

        Node pattern = new Node("", 0);

        for (int i = 0; i < N - 1; i++) {
            String mid = list.get(i).str;
            String right = list.get(i + 1).str;
            int midIdx = Math.min(list.get(i).idx, list.get(i + 1).idx);

            int size = Math.min(mid.length(), right.length());

            String tmp = "";
            for (int j = 0; j < size; j++) {
                if (right.charAt(j) == mid.charAt(j)) {
                    tmp += mid.charAt(j);
                }else{
                    break;
                }
            }

            if (pattern.str.length() < tmp.length()) {
                pattern = new Node(tmp, midIdx);
            } else if (pattern.str.length() == tmp.length() && pattern.idx > midIdx) {
                pattern = new Node(tmp, midIdx);
            }
        }

        int res = 2;
        for (int i = 0; i < copy.size(); i++) {
            String tmp = copy.get(i);
            int matchCount = 0;
            for (int j = 0; j < Math.min(tmp.length(), pattern.str.length()); j++) {
                if (tmp.charAt(j) == pattern.str.charAt(j)) {
                    matchCount++;
                } else {
                    break;
                }
            }

            if (matchCount == pattern.str.length()) {
                System.out.println(tmp);
                res--;
                if (res == 0) break;
            }
        }
    }
}