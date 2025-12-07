import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] arr1 = new int[N];

        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N - 2; i >= 0; i--) {
            if (arr1[i] >= arr1[i + 1]) {
                int diff = arr1[i] - arr1[i + 1] + 1;
                arr1[i] -= diff;
                answer += diff;
            }
        }

        System.out.println(answer);
    }
}