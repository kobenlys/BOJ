import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dice = new int[6];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        // 정육면체를 이루는 주사위들의 면이 보이는 경우
        long one = 5L * (N - 2) * (N - 2) + 4L * (N - 2); // 3면이 보이는 주사위 개수
        long two = 8L * (N - 2) + 4; // 2면이 보이는 주사위 개수
        int three = 4; // 1면만 보이는 주사위 개수
        long ans = 0;

        if (N == 1) {
            // 주사위가 한개일땐 1*1*1 -> 가장 큰 숫자 제외.
            Arrays.sort(dice);
            for (int i = 0; i < 5; i++) {
                ans += dice[i];
            }
            System.out.print(ans);
        } else {
            // 주사위가 1 이상일때 

            // 1면만 보이는 주사위 개수 * 가장 작은 숫자 
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 6; i++) {
                min = Math.min(min, dice[i]);
            }
            ans += min * one;

            // 2면이 보이는 주사위 개수 * 2면의 숫자들의 최소값
            min = Integer.MAX_VALUE;
            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    if (i + j != 5) {
                        min = Math.min(min, dice[i] + dice[j]);
                    }
                }
            }
            ans += min * two;

            // 3면이 보이는 주사위 개수 * 3면의 숫자들의 최소값
            min = 0;
            for (int i = 0; i < 3; i++) {
                min += Math.min(dice[i], dice[5 - i]);
            }

            ans += (long) min * three;
            System.out.print(ans);
        }
    }
}