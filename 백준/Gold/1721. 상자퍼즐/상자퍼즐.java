import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static Node[] arr1;
    public static Node[][] place;

    public static class Node {
        int[] box; // 북 동 남 서
        int topNumber;
        int index;
        int count;
        boolean isSelect = false;

        public Node(int[] box, int topNumber, int index, int count) {
            this.box = box;
            this.topNumber = topNumber;
            this.index = index;
            this.count = count;
        }

        public int getNorth() {
            return box[index];
        }

        public int getEast() {
            return box[(index + 1) % 4];
        }

        public int getSouth() {
            return box[(index + 2) % 4];
        }

        public int getWest() {
            return box[(index + 3) % 4];
        }
    }

    public static void printAnswer() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(place[i][j].topNumber).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(place[i][j].count).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static boolean isPossible(int placeIndex, int boxNum) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int x = placeIndex % N;
        int y = placeIndex / N;
        Node nowBox = arr1[boxNum];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {

                if (i == 0) {
                    if (nowBox.getNorth() != 0) return false;
                } else if (i == 1) {
                    if (nowBox.getSouth() != 0) return false;
                } else if (i == 2) {
                    if (nowBox.getWest() != 0) return false;
                } else {
                    if (nowBox.getEast() != 0) return false;
                }
            } else {
                Node sideBox = place[ny][nx];
                if (sideBox == null) {
                    continue;
                }

                if (i == 0) {
                    if (nowBox.getNorth() != sideBox.getSouth()) return false;
                } else if (i == 1) {
                    if (nowBox.getSouth() != sideBox.getNorth()) return false;
                } else if (i == 2) {
                    if (nowBox.getWest() != sideBox.getEast()) return false;
                } else {
                    if (nowBox.getEast() != sideBox.getWest()) return false;
                }
            }
        }
        place[y][x] = nowBox;
        return true;
    }

    public static void dfs(int start) {
        if (start == N * N) {
            printAnswer();
            System.exit(0);
        }

        for (int i = 0; i < N * N; i++) {
            Node nowBox = arr1[i];
            if (nowBox.isSelect) continue;

            for (int j = 1; j <= 4; j++) {
                int x = start % N;
                int y = start / N;
                int tmpIndex = nowBox.index;
                int tmpCount = nowBox.count;
                nowBox.index = 4 - j;
                nowBox.count = j % 4;
                if (isPossible(start, i)) {
                    nowBox.isSelect = true;
                    dfs(start + 1);
                    nowBox.isSelect = false;
                }

                place[y][x] = null;
                nowBox.index = tmpIndex;
                nowBox.count = tmpCount;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new Node[N * N];
        place = new Node[N][N];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int[] box = {n, e, s, w};
            arr1[i] = new Node(box, t, 0, 0);
        }

        dfs(0);
    }
}