import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        int beforeHome = 0;
        long answer = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N - 1; i >= 0; i--) {
            int home = arr1[i];
            int time = arr2[i];

            int needTime = Math.abs(home - beforeHome);
            beforeHome = home;
            answer += needTime;

            if (time > answer) {
                answer += time - answer;
            }
        }

        answer += arr1[0];
        System.out.println(answer);
    }
}