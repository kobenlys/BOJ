import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        HashSet<String> tmp = new HashSet<>();
        String[][] arr1 = new String[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = String.valueOf(str.charAt(j));
            }
        }

        for (int i = 0; i < M; i++) {
            String input = "";
            for (int j = 0; j < N; j++) {
                input += arr1[j][i];
            }
            set.add(input);
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {

            if (set.size() < M) {
                System.out.println(answer - 1);
                System.exit(0);
            }

            for (String s : set) {
                StringBuilder sb = new StringBuilder(s);
                sb.delete(0, 1);
                tmp.add(sb.toString());
            }

            set = new HashSet<>(tmp);
            tmp.clear();
            answer++;
        }

        System.out.println(answer -1);
    }
}