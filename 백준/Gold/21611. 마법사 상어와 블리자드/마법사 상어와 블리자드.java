import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, oneBead, twoBead, threeBead;
    public static int[][] arr1;
    public static List<Integer> list = new ArrayList<>();


    public static void useMagic(int dir, int range) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int x = N / 2;
        int y = x;
        dir--;

        for (int i = 1; i <= range; i++) {

            int nx = x + dx[dir] * i;
            int ny = y + dy[dir] * i;
            arr1[ny][nx] = 0;
        }
    }

    public static void beadAdd() {
        List<Integer> tmp = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int color = list.get(i);
            int cnt = 1;

            for (int j = i + 1; j < list.size(); j++) {

                if (color == list.get(j)) {
                    cnt++;
                    if (j == list.size() - 1) {
                        i = j;
                    }
                } else {
                    i = j - 1;
                    break;
                }
            }
            tmp.add(cnt);
            tmp.add(color);
        }

        list = tmp;
    }

    public static boolean beadDelete() {
        boolean isUpdate = false;
        for (int i = 0; i < list.size(); i++) {

            int color = list.get(i);
            int cnt = 1;

            for (int j = i + 1; j < list.size(); j++) {
                if (color == list.get(j)) {
                    cnt++;
                } else {
                    break;
                }
            }

            if (cnt >= 4) {
                if (color == 1) oneBead += cnt;
                if (color == 2) twoBead += cnt;
                if (color == 3) threeBead += cnt;
                for (int j = 0; j < cnt; j++) {
                    list.remove(i);
                }
                isUpdate = true;
//                System.out.println(oneBead + twoBead * 2 + threeBead * 3);
//                System.out.println(list);
            }
        }
        return isUpdate;
    }

    public static void makeList() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        list.clear();
        int time = 0;

        int x = N / 2;
        int y = N / 2;
        int len = 0;

        while (time <= N * 2) {
            for (int i = 0; i < 4; i++) {
                if (i == 2 || i == 0) len++;

                for (int j = 0; j < len; j++) {
                    x += dx[i];
                    y += dy[i];
                    if (x < 0 || y < 0 || x >= N || y >= N) continue;
                    if (arr1[y][x] == 0) continue;
                    list.add(arr1[y][x]);
                }
            }
            time++;
        }
    }

    public static void makeMap() {

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        arr1 = new int[N][N];

        int time = 0;
        int idx = 0;

        int x = N / 2;
        int y = N / 2;
        int len = 0;

        st:
        while (time <= N * 2 && !list.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                if (i == 2 || i == 0) len++;

                for (int j = 0; j < len; j++) {
                    x += dx[i];
                    y += dy[i];
                    if (x < 0 || y < 0 || x >= N || y >= N) continue;
                    arr1[y][x] = list.get(idx++);
                    if (list.size() == idx) break st;
                }
            }
            time++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int range = Integer.parseInt(st.nextToken());
            makeList();
            
            useMagic(dir, range);

            makeList();

            while(true){
                if(!beadDelete()) break;
            }

            beadAdd();
            
            makeMap();
            
        }
        System.out.println(oneBead + twoBead * 2 + threeBead * 3);
    }
}