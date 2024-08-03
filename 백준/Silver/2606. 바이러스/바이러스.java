import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static int[][] arr1;
    public static boolean[] vi;
    public static int pc, N, cnt;

    public static void algorithm(int now) {

        vi[now] = true;

        for (int i = 1; i <= pc; i++) {
            if (arr1[now][i] == 1 && !vi[i]) {
                
                cnt++;
                algorithm(i);

            }
        }

    }

    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        pc = Integer.parseInt(br.readLine()); // 컴퓨터 수
        N = Integer.parseInt(br.readLine()); // 컴퓨터 커넥션 수

        arr1 = new int[pc+1][pc+1];
        vi = new boolean[pc+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr1[a][b] = arr1[b][a] = 1;

        }



        algorithm(1);
        System.out.println(cnt);

    }
}