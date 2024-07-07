import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        int cnt = 0;
        int answer = 0;
        int[] arr1 = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            if (arr1[i] >= K) {
                cnt += arr1[i] / K;
                arr1[i] %= K;
            }
        }

        Arrays.sort(arr1);

        int tmp = 0, max = 0;
        for (int i = 0; i < K; i++) {
            if (arr1[i] == 0) continue;
            tmp++; // 만약 같은 색상의 공을 다 먹었다면 tmp세기 (세로먹기)

            int tmpMax = max;
            // (가로 먹기) = 모두 다른 색상 공 먹기.
            // 모두 다른 색상의 공을 K개 이하로 먹는다면, 당연하게도 가장 많이 남아 있는 공 개수 만큼 먹겠지?
            max = Math.max(max, arr1[i]);
            // ex  1 1 1 4 일때 tmp증가폭
            // 한 턴 세로먹기에 필요한 박스(1개)보다 -> 이번턴 가로먹기에 필요한 박스 수가 더 많다면
            // 이번 턴 공은 세로먹기 하도록 값 조정
            // max(4) -= max(4) - tmpMax(1) - 1
            // max = 2 임 => 이전 가로먹기 개수 + 이번턴 세로먹기 개수 = 2 
            
            /*if (1 < max - tmpMax) max -= max - tmpMax-1;*/
            if (1 < max - tmpMax) max = tmpMax+1;
        }

        answer = cnt + Math.min(max, tmp);
        System.out.println(answer);
    }
}