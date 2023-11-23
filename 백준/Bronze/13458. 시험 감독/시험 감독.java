import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[1000001];
        int[] arr1 = new int[T];

        // 강의실 당 학생 수 입력
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < T; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 총 감독관
        int M = Integer.parseInt(st.nextToken()); // 부 감독관

        // 답의 최대 범위가 2^31 이상이다. 자료형을 long 으로 바꿔준다.
        long res = 0;
        for (int i = 0; i < T; i++) {
            int num = arr1[i];

            if (dp[num] == 0) { // dp 에 저장된 값이 아니라면

                int flag = num - N;
                if (flag < 0) {
                    // num이  0 보다 작아지는 상황 방지
                    flag = 0;
                }

                if (flag % M != 0) {
                    // M 으로 나눴을때 나머지가 있다면 = flag/M + 총감독관 + 나머지를 채우는 부감독관
                    dp[num] = (flag / M) + 2;
                    res += dp[num];
                }else{
                    // M 으로 나눴을때 나머지가 없다면 = flag/M + 총감독관
                    dp[num] = (flag / M) + 1;
                    res += dp[num];
                }

            }else{ // dp에 저장된 값이 있다면 (중복 계산 방지)
                res += dp[num];
            }
        }

        System.out.println(res);

    }
}
