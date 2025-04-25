import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int tmp = -1;
        int answer = 0;

        int[] arr1 = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {

            if (tmp == -1) {
                tmp = arr1[i];
                answer++;
                continue;
            }

            if (tmp + K < arr1[i]) {
                tmp = arr1[i];
                answer++;
            }
        }

        System.out.println(answer);
    }
}