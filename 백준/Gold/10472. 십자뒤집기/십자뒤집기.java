import java.io.*;
import java.util.*;

public class Main {
    public static HashMap<String, Integer> map = new HashMap<>();

    public static class node {
        String str;
        int cnt;
        public node(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }

    public static void bfs(String start) {
        Queue<node> qu = new LinkedList<>();
        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, -1, 1, 0, 0};

        qu.offer(new node(start, 0));
        map.put(start, 0);

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    StringBuilder sb = new StringBuilder(nd.str);

                    for (int k = 0; k < 5; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];

                        if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;
                        int strIdx = nx + ny * 3;

                        if (nd.str.charAt(strIdx) == '*') {
                            sb.setCharAt(strIdx, '.');
                        } else {
                            sb.setCharAt(strIdx, '*');
                        }
                    }

                    if (!map.containsKey(sb.toString())) {
                        map.put(sb.toString(), nd.cnt + 1);
                        qu.offer(new node(sb.toString(), nd.cnt + 1));
                    } else {
                        if (map.get(sb.toString()) > nd.cnt + 1) {
                            map.put(sb.toString(), nd.cnt + 1);
                            qu.offer(new node(sb.toString(), nd.cnt + 1));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        String base = ".........";
        bfs(base);

        while (T-- > 0) {
            String str1 = "";

            for (int i = 0; i < 3; i++) {
                str1 = str1.concat(br.readLine());
            }

            sb.append(map.get(str1)).append("\n");
        }
        System.out.print(sb);
    }
}