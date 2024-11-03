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

        Arrays.sort(arr1);

        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, arr1[i] * (N - i));
        }

        System.out.println(answer);
    }
}