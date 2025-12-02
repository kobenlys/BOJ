import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[367];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            for (int j = s; j <= e; j++) {
                arr1[j]++;
            }
        }

        int answer = 0;

        for (int i = 1; i <= 365; i++) {

            if (arr1[i] > 0) {
                int height = arr1[i];
                for (int j = i + 1; j <= 366; j++) {
                    if (arr1[j] > 0) {
                        height = Math.max(height, arr1[j]);

                    } else if (arr1[j] == 0) {
                        answer += (j - i) * height;
                        i = j - 1;
                        break;
                    }
                }

            }
        }

        System.out.println(answer);
    }
}