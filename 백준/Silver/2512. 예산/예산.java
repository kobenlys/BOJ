import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;

    public static int check(int n) {

        int sum = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] >= n) {
                sum += n;
            } else {
                sum += arr1[i];
            }
        }
        return sum;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int answer = 0;
        arr1 = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        Arrays.sort(arr1);

        int left = 0, right = arr1[N - 1];

        while (left <= right) {

            int mid = (left + right) / 2;
            int res = check(mid);

            if (M < res) {
                right = mid - 1;
            } else {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
        }
        System.out.print(answer);
    }
}