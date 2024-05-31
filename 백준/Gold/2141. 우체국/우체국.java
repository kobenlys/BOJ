import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr1 = new int[N][2];
        long tmp = 0;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr1[i][0] = Integer.parseInt(st.nextToken());
            arr1[i][1] = Integer.parseInt(st.nextToken());
            tmp += arr1[i][1];
        }

        Arrays.sort(arr1, Comparator.comparingInt(o -> o[0]));

        for (int i = 0; i < N; i++) {

            sum += arr1[i][1];

            if ((tmp+1) / 2 <= sum) {
                System.out.println(arr1[i][0]);
                break;
            }
        }
    }
}