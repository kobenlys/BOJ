import java.io.*;
import java.util.*;

public class Solution {
    public static int N;
    public static int[][] arr1;
    public static int[][] res;

    public static void up() {
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                int num = arr1[j][i];
                if (num != 0) {
                    list.add(num);
                }
            }

            for (int j = 0; j < list.size() - 1; j++) {
                if (Objects.equals(list.get(j), list.get(j + 1))) {
                    list.set(j, list.get(j) * 2);
                    list.remove(j + 1);
                }
            }

            for (int j = 0; j < list.size(); j++) {
                res[j][i] = list.get(j);
            }
        }
    }

    public static void down() {
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int j = N - 1; j >= 0; j--) {
                int num = arr1[j][i];
                if (num != 0) {
                    list.add(num);
                }
            }

            for (int j = 0; j < list.size() - 1; j++) {
                if (Objects.equals(list.get(j), list.get(j + 1))) {
                    list.set(j, list.get(j) * 2);
                    list.remove(j + 1);
                }
            }

            for (int j = 0; j < list.size(); j++) {
                res[(N - 1) - j][i] = list.get(j);
            }
        }
    }

    public static void left() {
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                int num = arr1[i][j];
                if (num != 0) {
                    list.add(num);
                }
            }

            for (int j = 0; j < list.size() - 1; j++) {
                if (Objects.equals(list.get(j), list.get(j + 1))) {
                    list.set(j, list.get(j) * 2);
                    list.remove(j + 1);
                }
            }

            for (int j = 0; j < list.size(); j++) {
                res[i][j] = list.get(j);
            }
        }
    }

    public static void right() {
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int j = N - 1; j >= 0; j--) {
                int num = arr1[i][j];
                if (num != 0) {
                    list.add(num);
                }
            }

            for (int j = 0; j < list.size() - 1; j++) {
                if (Objects.equals(list.get(j), list.get(j + 1))) {
                    list.set(j, list.get(j) * 2);
                    list.remove(j + 1);
                }
            }

            for (int j = 0; j < list.size(); j++) {
                res[i][(N - 1) - j] = list.get(j);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            String input = st.nextToken();

            arr1 = new int[N][N];
            res = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (input.equals("up")) {
                up();
            } else if (input.equals("down")) {
                down();
            } else if (input.equals("left")) {
                left();
            } else {
                right();
            }

            sb.append("#").append(tc).append(" ").append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(res[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}


