import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr1 = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr1[i][0] = Integer.parseInt(st.nextToken());
            arr1[i][1] = Integer.parseInt(st.nextToken());
        }
        // Comparator 사용한 정렬
        Arrays.sort(arr1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // x좌표 비교대상이 같다면 y좌표 비교
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });

        for (int[] e : arr1) {
            sb.append(e[0]).append(" ").append(e[1]).append("\n");
        }
        System.out.print(sb);
    }
}