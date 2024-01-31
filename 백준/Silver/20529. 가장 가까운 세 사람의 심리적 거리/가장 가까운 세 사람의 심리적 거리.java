import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        // 최소 33개 일때 같은 mbti 3개가 있다.

        for (int tc = 0; tc < T; tc++) {

            int N = Integer.parseInt(br.readLine());
            int min = 12;
            String[] arr1 = new String[N];

            st = new StringTokenizer(br.readLine(), " ");
            if (N > 32) {
                sb.append(0).append("\n");
            } else {
                for (int i = 0; i < N; i++) {
                    arr1[i] = st.nextToken();
                }

                for (int i = 0; i < N; i++) {
                    for (int j = i+1; j < N; j++) {
                        for (int k = j+1; k < N; k++) {
                            int cnt = 0;
                            for (int l = 0; l < 4; l++) {

                                cnt += arr1[i].charAt(l) == arr1[j].charAt(l) ? 0 : 1;
                                cnt += arr1[i].charAt(l) == arr1[k].charAt(l) ? 0 : 1;
                                cnt += arr1[j].charAt(l) == arr1[k].charAt(l) ? 0 : 1;

                            }
                            min = Math.min(min, cnt);
                        }
                    }
                }
                sb.append(min).append("\n");
            }
        }
        System.out.print(sb);
    }
}
