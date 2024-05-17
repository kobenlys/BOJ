import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;

    public static int[] algorithm(int tmp) {
        int[] list = new int[N + 1];
        int answer = 0;
        list[1] = tmp;

        for (int i = 1; i < N; i++) {

            int tmpS = arr1[i][0];
            int tmpE = arr1[i][1];

            int tmp1 = Math.abs(tmp - tmpS);
            int tmp2 = Math.abs(tmp - tmpE);

            if (tmpS <= tmp && tmp <= tmpE) {
                list[i + 1] = tmp;
                continue;
            }

            if (tmp1 > tmp2) {
                list[i + 1] = tmpE;
                tmp = tmpE;
                answer += tmp2;
            } else {
                list[i + 1] = tmpS;
                tmp = tmpS;
                answer += tmp1;
            }
        }

        list[0] = answer;
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr1[i][0] = Integer.parseInt(st.nextToken());
            arr1[i][1] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            System.out.println(0);
            System.out.println(arr1[0][0]);
            System.exit(0);
        }

        int[] answerList = null;
        int min = Integer.MAX_VALUE;

        for (int i = arr1[0][0]; i <= arr1[0][1]; i++) {
            int[] res = algorithm(i);

            if (min > res[0]) {
                min = res[0];
                answerList = res;
            }
        }

        for (int e : answerList) {
            sb.append(e).append("\n");
        }


        System.out.print(sb);
    }
}