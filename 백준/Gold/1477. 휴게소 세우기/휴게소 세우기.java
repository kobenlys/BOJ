import java.io.*;
import java.util.*;

public class Main {

    public static boolean isPossible(int target, int[] arr1, int M) {
        int count = 0;

        for (int i = 1; i < arr1.length; i++) {
            if(arr1[i] - arr1[i-1] <= target) continue;
            int time = 0;
            if ((arr1[i] - arr1[i - 1]) % target == 0) {
                time = (arr1[i] - arr1[i - 1]) / target -1;
            }else{
                time = (arr1[i] - arr1[i - 1]) / target;
            }
            count += time;
        }
        return count <= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 휴게소 M개를 더 세우려고 함
        // 휴게소는 중복이 안됨
        // 고속도로 끝에 휴게소를 세울 수 없고, 정수 위치에만 세울 수 있음
        // 모든 휴게소 방문ㄴ 할거임 그러니 M개를 더 지어서 휴게소가 없는 구간의 길이의 최댓값을 최소로 할거임

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr1 = new int[N+2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        arr1[N + 1] = K;
        Arrays.sort(arr1);
        int left = 1;
        int right = K;

        while (left < right) {
            int mid = (left + right) >> 1;
            if (!isPossible(mid, arr1, M)) {
                left = mid + 1;
            }else{
                right = mid;
            }
        }

        System.out.println(right);
    }
}