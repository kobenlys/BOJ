import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, res, flag;
    public static int[] arr1;

    public static void swap(int left, int right) {
        int temp = arr1[left];
        arr1[left] = arr1[right];
        arr1[right] = temp;
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int left = N-1;
        int right = N - 1;

        while (left > 0 && arr1[left - 1] <= arr1[left]) {
            left--;
        }

        if (left <= 0) {
            System.out.println(-1);
            System.exit(0);
        }


        while (arr1[left - 1] <= arr1[right]) {
            right--;
        }

        swap(left-1, right);
        right = N - 1;
        while (left < right) {
            swap(left, right);
            left++;
            right--;
        }

        for (int i = 0; i < N; i++) {
            sb.append(arr1[i]).append(" ");
        }
        System.out.println(sb);
    }
}
