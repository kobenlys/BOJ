import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static int N, S, cnt;

    public static void algorithm(int start, int sum){ // BackTracking 알고리즘

        // S와 부분수열 합 이 같다면 cnt+1
        // N == start 일때 sum과 S 를 비교
        if (start == N) {
            if (sum == S) {
                cnt++;
            }
            return;
        }


        // arr1[start]를 사용하는 경우
        algorithm(start + 1, sum + arr1[start]);
        // arr1[start]를 사용하지 않는 경우
        algorithm(start + 1, sum);


    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr1 = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }


        algorithm(0,0);
        // S = 0인 경우는 백트래킹 알고리즘이용시 모두 다 선택되지 않을 경우(sum = 0)
        // 도 있기 때문에 예외처리한다
        if (S == 0) {
            cnt--;
        }
        System.out.println(cnt);
    }
}


