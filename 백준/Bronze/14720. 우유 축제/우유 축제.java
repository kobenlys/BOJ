import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        // 우유 마시는 규칙 저장
        int[] arr1 = {0, 1, 2};
        int cnt = 0;
        int milk = 0;
        st = new StringTokenizer(br.readLine(), " ");
        
        // st에 요소가 존재한다면 반복
        while (st.hasMoreElements()) {
            int store = Integer.parseInt(st.nextToken());
            // arr1 배열 이용하여 우유 마시는 규칙 순서 정함
            if (store == arr1[cnt]) {
                // 다음 우유 먹기 위해 cnt++
                cnt++;
                milk++;
                // 다시 딸기우유를 먹어야 하기 위해 cnt 초기화
                if (cnt == 3) {
                    cnt = 0;
                }
            }
        }
        System.out.print(milk);
    }
}