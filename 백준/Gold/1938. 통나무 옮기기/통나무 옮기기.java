import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static Map<String, Integer> map = new HashMap<>();

    public static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    public static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

    public static class Node {

        String map;
        int[] pos;

        public Node(String map, int[] pos) {
            this.map = map;
            this.pos = pos;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    public static int convertPosition(int x, int y) {
        return N * y + x;
    }

    public static int[] decodePosition(int index) {
        return new int[]{index % N, index / N};
    }

    public static Node moveForward90(Node mapInfo) {

        int[] nextPos = new int[3];
        int idx = 0;
        StringBuilder sb = new StringBuilder(mapInfo.map);

        int cx = mapInfo.pos[1] % N;
        int cy = mapInfo.pos[1] / N;

        for (int i = 0; i < 8; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if (!isRange(nx, ny)) {
                continue;
            }
            if (mapInfo.map.charAt(convertPosition(nx, ny)) == '1') {
                return null;
            }
        }

        for (int i = 0; i < 3; i++) {
            int[] pos = decodePosition(mapInfo.pos[i]);
            int nx = cx - (pos[1] - cy);
            int ny = cy + (pos[0] - cx);
            if (!isRange(nx, ny)) {
                return null;
            }
            int nextIndex = convertPosition(nx, ny);

            sb.setCharAt(mapInfo.pos[i], '0');
            nextPos[idx++] = nextIndex;
            sb.setCharAt(nextIndex, 'B');
        }

        return new Node(sb.toString(), nextPos);
    }

    public static Node moveDirSide(Node mapInfo, int dir) {

        int[] nextPos = new int[3];
        int idx = 0;
        StringBuilder sb = new StringBuilder(mapInfo.map);

        for (int i = 0; i < 3; i++) {
            int[] pos = decodePosition(mapInfo.pos[i]);

            int nx = pos[0] + dx[dir];
            int ny = pos[1] + dy[dir];
            int nextIndex = convertPosition(nx, ny);
            if (!isRange(nx, ny) || mapInfo.map.charAt(nextIndex) == '1') {
                return null;
            }

            sb.setCharAt(mapInfo.pos[i], '0');
            nextPos[idx++] = nextIndex;
        }

        for (int i = 0; i < 3; i++) {
            sb.setCharAt(nextPos[i], 'B');
        }

        return new Node(sb.toString(), nextPos);
    }

    public static void bfs(Node initMap) {

        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(initMap);

        while (!qu.isEmpty()) {

            Node nowMap = qu.poll();

            for (int i = 0; i < 5; i++) {
                Node next;

                if (i == 4) {
                    next = moveForward90(nowMap);
                }else{
                    next = moveDirSide(nowMap, i);
                }
                if (next == null) {
                    continue;
                }
//                System.out.println("@@@@@@@@@@@@@@@@@@ dir : " + (i+1));
//                printMatrix(nowMap.map);
//                printMatrix(next.map);

                if (!map.containsKey(next.map)) {
                    map.put(next.map, map.get(nowMap.map) + 1);
                    qu.offer(next);
                }
            }
        }
    }

    public static String makeBFSKey(String string) {
        StringBuilder sb = new StringBuilder(string);
        for (int i = 0; i < string.length(); i++) {
            if (sb.charAt(i) == 'E') {
                sb.setCharAt(i, '0');
            }
        }
        return sb.toString();
    }

//    public static void printMatrix(String string) {
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(string.charAt(convertPosition(j,i)));
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] pos = new int[3];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            sb.append(str);

            for (int j = 0; j < N; j++) {
                if (str.charAt(j) == 'B') {
                    pos[idx++] = convertPosition(j, i);
                }
            }
        }
        map.put(makeBFSKey(sb.toString()), 0);
//        System.out.println("before");
//        System.out.println(sb.toString());
//        System.out.println();

        bfs(new Node(makeBFSKey(sb.toString()), pos));

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == 'B') {
                sb.setCharAt(i, '0');
            } else if (sb.charAt(i) == 'E') {
                sb.setCharAt(i, 'B');
            }
        }



//        System.out.println("after");
//        System.out.println(sb.toString());
//        System.out.println();
//
//        System.out.println("map size : " + map.size());
        System.out.println(map.getOrDefault(sb.toString(), 0));
    }
}