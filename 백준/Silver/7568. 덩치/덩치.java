import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr1 = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr1[i][0] = Integer.parseInt(st.nextToken());
            arr1[i][1] = Integer.parseInt(st.nextToken());
            arr1[i][2]++;
        }

        for (int i = 0; i < N; i++) {
            int w = arr1[i][0];
            int t = arr1[i][1];
            // A 와 B 를 비교해서 키, 몸무게를 다 진다면 등수++
            // A 와 B 를 비교해서 키, 몸무게를 비긴다면 등수는 ==
            // A 와 B 를 비교해서 키, 몸무게를 이긴다면 등수는 == (1등급 부터 시작했기 때문에.)
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                int nw = arr1[j][0];
                int nt = arr1[j][1];
                // 1등급 부터 시작하기 때문에 키, 몸무게 다 지는 경우만 등수++
                if (w < nw && t < nt) {
                    arr1[i][2]++;
                }
            }
        }

        for (int[] e : arr1) {
            sb.append(e[2]).append(" ");
        }
        System.out.println(sb);
    }
}