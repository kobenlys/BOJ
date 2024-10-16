import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        arr1 = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i < N; i++) {
            if (arr1[i - 2] > 0 && arr1[i - 1] > 0 && arr1[i] > 0) {
                if (arr1[i - 1] <= arr1[i]) {

                    int ramen = Math.min(arr1[i - 2], Math.min(arr1[i - 1], arr1[i]));
                    arr1[i - 2] -= ramen;
                    arr1[i - 1] -= ramen;
                    arr1[i] -= ramen;
                    answer += ramen * 7;
                } else {

                    int ramen = Math.abs(arr1[i - 1] - arr1[i]);
                    ramen = Math.min(ramen, arr1[i - 2]);
                    arr1[i - 2] -= ramen;
                    arr1[i - 1] -= ramen;
                    answer += ramen * 5;
                    i--;
                }
            }
        }

        for (int i = 1; i < N; i++) {
            if (arr1[i - 1] > 0 && arr1[i] > 0) {
                int ramen = Math.min(arr1[i - 1], arr1[i]);
                arr1[i] -= ramen;
                arr1[i - 1] -= ramen;
                answer += ramen * 5;
            }
        }

        for (int i = 0; i < N; i++) {
            if (arr1[i] > 0) {
                answer += arr1[i] * 3;
            }
        }

        System.out.println(answer);
    }
}