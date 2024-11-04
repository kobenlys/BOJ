import java.io.*;
import java.util.*;

public class Main {
    public static char[][] arr1;
    public static Set<String> set = new HashSet<>();
    public static String answer = "";
    public static int score = 0;
    public static boolean[][] vi = new boolean[4][4];
    public static Node root = new Node();

    public static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    public static int[] dy = {-1, 1, 0, 0, 1, 1, -1, -1};

    public static class Node {
        Map<Character, Node> map = new HashMap<>();
        boolean isEnd;
    }

    public static void insert(String input) {
        Node parent = root;

        for (int i = 0; i < input.length(); i++) {
            parent = parent.map.computeIfAbsent(input.charAt(i), e -> new Node());
        }
        parent.isEnd = true;
    }

    public static boolean search(String boogle) {
        Node parent = root;

        for (int i = 0; i < boogle.length(); i++) {
            char tmp = boogle.charAt(i);
            parent = parent.map.getOrDefault(tmp, null);
            if (parent == null) return false;
        }

        return parent.isEnd;
    }

    public static int calculateScore(String boogle) {
        if (boogle.length() <= 2) {
            return 0;
        } else if (boogle.length() <= 4) {
            return 1;
        } else if (boogle.length() == 5) {
            return 2;
        } else if (boogle.length() == 6) {
            return 3;
        } else if (boogle.length() == 7) {
            return 5;
        } else {
            return 11;
        }
    }

    public static void dfs(int x, int y, StringBuilder sb, int target) {

        if (sb.length() == target) {
            if (!set.contains(sb.toString()) && search(sb.toString())) {

                if (answer.length() <= sb.length()) {
                    if (answer.length() == sb.length()) {
                        List<String> tmp = new ArrayList<>();
                        tmp.add(answer);
                        tmp.add(sb.toString());
                        Collections.sort(tmp);
                        answer = tmp.get(0);
                    }else{
                        answer = sb.toString();
                    }
                }
                set.add(sb.toString());
                score += calculateScore(sb.toString());
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
            if (!vi[ny][nx]) {
                vi[ny][nx] = true;
                sb.append(arr1[ny][nx]);
                dfs(nx, ny, sb, target);
                sb.deleteCharAt(sb.length() - 1);
                vi[ny][nx] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            insert(br.readLine());
        }

        arr1 = new char[4][4];
        br.readLine();
        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {

            set.clear();
            answer = "";
            score = 0;

            for (int i = 0; i < 4; i++) {
                String str = br.readLine();
                for (int j = 0; j < 4; j++) {
                    arr1[i][j] = str.charAt(j);
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 1; k <= 8; k++) {
                        vi[i][j] = true;
                        dfs(j, i, new StringBuilder(String.valueOf(arr1[i][j])), k);
                        vi[i][j] = false;
                    }
                }
            }

            if (!answer.equals("")) {
                sb.append(score + " ")
                        .append(answer + " ").append(set.size()).append("\n");
            }


            if (M != 0) {
                br.readLine();
            }
        }
        System.out.println(sb);
    }
}