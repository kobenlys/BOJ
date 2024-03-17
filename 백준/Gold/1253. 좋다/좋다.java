import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, ans;
    public static int[] arr1;

    public static boolean twoPointer1(int idx) {
        // idx 기준 left,right는 양 옆으로 넓혀가며 탐색
        int left = idx-1, right = idx + 1;
        int target = arr1[idx];
        while (left >= 0 && right < N) {

            if (target < arr1[left] + arr1[right]) {
                left--;
            } else if (target > arr1[left] + arr1[right]) {
                right++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean twoPointer2(int idx) {
        // idx를 제외한 0~N-1번 인덱스 까지 좁혀가며 탐색
        int left = 0, right = N-1;
        int target = arr1[idx];
        while (left < right) {
            // -8 -7 -6 -3 -2 -1 0 1 4 5 6 12 654 234
            // -8 -7 -6 -3 -2 0 1 4 5 6 12 654 234

            if (left == idx) {
                left++;
                continue;
            } else if (right == idx) {
                right--;
                continue;
            }

            if (target > arr1[left] + arr1[right]) {
                left++;
            } else if (target < arr1[left] + arr1[right]) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr1 = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);

        for (int i = 0; i < N; i++) {
            // 둘중 하나만 참이면 ans+1 해준다.
            if (twoPointer1(i)) {
                ans++;
            } else if (twoPointer2(i)) {
                ans++;
            }

        }
        System.out.print(ans);
    }
}