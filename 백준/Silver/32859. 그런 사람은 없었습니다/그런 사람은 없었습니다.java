import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int S = Integer.parseInt(br.readLine());
        int[][] arr1 = new int[M][2];
        Set<Integer> isSubmit = new HashSet<>();
        List<Integer> answer = new ArrayList<>();
        // t == 0 폼 제출, t == 1 i 번째 회원이 입금

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int stat = Integer.parseInt(st.nextToken());
            arr1[i][0] = num;
            arr1[i][1] = stat;
        }

        for (int i = 0; i < M; i++) {

            if (arr1[i][1] == 0) {
                isSubmit.add(arr1[i][0]);
                continue;
            }

            if (isSubmit.contains(arr1[i][0])) {
                continue;
            }

            int cnt = 0;
            for (int j = i + 1; j < M; j++) {
                if (arr1[i][0] == arr1[j][0] && arr1[j][1] == 0) {
                    break;
                }

                if (arr1[j][1] == 0) {
                    cnt++;
                }
            }

            if (cnt >= S) {
                answer.add(arr1[i][0]);
            }
        }

        Collections.sort(answer);

        for (int e : answer) {
            sb.append(e).append("\n");
        }

        System.out.println(sb.toString().isEmpty() ? -1 : sb);
    }
}