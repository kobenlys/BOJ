import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static int[] dp;
    
    // 이분탐색 + LIS 알고리즘
    public static int LIS(int num, int left, int right, int size) {
        int res = 0;

        while (left <= right) {
            // 이분탐색 mid 값
            int mid = (left + right) / 2;
            // num 보다 dp[mid] 값이 크다면 최대값(right) 낮추기
            if (num <= dp[mid]) {
                right = mid - 1;
                res = mid;
            } else { // num 보다 dp[mid] 값이 작다면 최소값(left) 올리기
                left = mid + 1;
            }
        }
        // left == size 라면 dp배열안의 요소들은 num 보다 작다 -> dp배열 맨 끝에 입력
        // 즉 right 부분이 한번이라도 줄어드면(dp배열안 요소가 num보다 크다는 뜻). left == size 불가능.
        if (left == size) {
            return -1;
        } else {
            // left != size 라면 dp배열안 요소들 중 크거나 같은 요소가 있음 -> 해당 자리에 입력
            // num == dp[i] 여도 right = mid -1 때문에 left와 size는 같을 수 없다.
            // dp[res] 에 값을 덮어씌우자.
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        arr1 = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // LIS - 이분탐색으로 O(NlognN) 시간복잡도 가지게 구현
        int len = 0;
        for (int i = 0; i < N; i++) {
            // 저장해줄 위치 이분탐색으로 찾기
            int site = LIS(arr1[i], 0, len, len + 1);

            if (site == -1) { // dp배열에 arr1[i] 보다 크거나 같은 값 존재 x -> dp배열의 맨끝에 입력.
                dp[len] = arr1[i];
                len++;
            } else { // dp배열에 arr1[i] 크거나 같은 값은 값 존재 -> dp배열의 해당자리에 덮어씌우기
                dp[site] = arr1[i];
            }
        }
        System.out.println(len);
    }
}