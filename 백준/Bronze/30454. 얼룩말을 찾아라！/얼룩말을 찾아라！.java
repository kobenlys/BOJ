import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int max = 0;
        int flag = 0;

        for (int i = 0; i < N; i++) {

            String input = br.readLine();
            int cnt = 0;
            int left = 0;
            int right = 0;
            
            while (true) { // 투포인터 알고리즘

                if (input.charAt(left) == '1' && input.charAt(right) == '0'
                        || input.charAt(left) == '1' && right == L - 1) {
                    cnt++;
                    left = right;
                    if (right == L - 1) {
                        break;
                    }
                } else if (input.charAt(left) == '1' && input.charAt(right) == '1') {
                    right++;

                } else if (input.charAt(left) == '0' && input.charAt(right) == '0') {
                    if (left == L - 1 && right == L - 1) {
                        break;
                    }
                    left++;
                    right++;
                }


            }

            if (max < cnt) {
                flag = 1;
                max = cnt;
            } else if (max == cnt) {
                flag++;
            }


        }

        System.out.println(max + " " + flag);

    }
}
