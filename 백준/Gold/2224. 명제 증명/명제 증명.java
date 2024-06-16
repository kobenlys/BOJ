import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<ArrayList<Integer>> arr1;
    public static boolean[] upperVi;
    public static boolean[] lowerVi;

    public static void dfs(int idx) {

        for (int e : arr1.get(idx)) {
            if (e < 26) {
                if (!upperVi[e]) {
                    upperVi[e] = true;
                    dfs(e);
                }
            } else {
                if (!lowerVi[e - 26]) {
                    lowerVi[e - 26] = true;
                    dfs(e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        arr1 = new ArrayList<>();


        for (int i = 0; i <= 53; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            char ch1 = input.charAt(0);
            char ch2 = input.charAt(input.length() - 1);
            int s = Character.isUpperCase(ch1) ? ch1 - 'A' : ch1 - 'a' + 26;
            int e = Character.isUpperCase(ch2) ? ch2 - 'A' : ch2 - 'a' + 26;

            if (arr1.get(s).contains(e)) continue;
            arr1.get(s).add(e);
        }

        for (int i = 0; i < 26; i++) {
            upperVi = new boolean[26];
            lowerVi = new boolean[26];
            dfs(i);
            for (int j = 0; j < 26; j++) {
                if (i == j) continue;
                if (upperVi[j]) {
                    sb.append((char) (i + 'A')).append(" => ").append((char) (j + 'A'));
                    answer++;
                    sb.append("\n");
                }
            }
            for (int j = 0; j < 26; j++) {
                if (lowerVi[j]) {
                    sb.append((char) (i + 'A')).append(" => ").append((char) (j + 'a'));
                    answer++;
                    sb.append("\n");
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            upperVi = new boolean[26];
            lowerVi = new boolean[26];
            dfs(i + 26);
            for (int j = 0; j < 26; j++) {
                if (upperVi[j]) {
                    sb.append((char) (i + 'a')).append(" => ").append((char) (j + 'A'));
                    answer++;
                    sb.append("\n");
                }
            }
            for (int j = 0; j < 26; j++) {
                if (i == j) continue;
                if (lowerVi[j]) {
                    sb.append((char) (i + 'a')).append(" => ").append((char) (j + 'a'));
                    answer++;
                    sb.append("\n");
                }
            }
        }
        System.out.println(answer);
        System.out.print(sb);
    }
}