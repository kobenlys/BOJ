import java.io.*;
import java.util.*;

public class Main {
    public static int N, C;
    public static int[] arr1;

    public static boolean findLength(int len) {
        int cnt = 1;
        int home1 = arr1[0];

        for (int i = 0; i < N - 1;) {
            int tmp = arr1[i] + len;
            int j;
            for (j = i + 1; j < N; j++) {
                if (tmp <= arr1[j]) {
                    cnt++;
                    break;
                }
            }
            i = j; // i를 j로 갱신하여 다음 비교를 위해 인덱스를 설정
        }

        if (cnt >= C) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr1 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr1);


        int left = 0, right = 1_000_000_000;
        int answer = 0;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (!findLength(mid)) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }
        System.out.print(answer);
    }
}