import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] arr1 = new int[1001];
        int[] arr2 = new int[1001];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr1[a]++;
            arr2[b]++;
        }

        for (int j = 1; j < 1001; j++) {
            if (arr1[j] == 0) {
                continue;
            }
            if (arr1[j] == 1) {
                System.out.print(j + " ");
            }
        }
        for (int j = 1; j < 1001; j++) {
            if (arr2[j] == 0) {
                continue;
            }
            if (arr2[j] == 1) {
                System.out.print(j);
            }
        }
    }
}