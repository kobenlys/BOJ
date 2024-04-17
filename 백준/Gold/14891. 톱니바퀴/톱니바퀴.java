import java.io.*;
import java.util.*;

public class Main {
    public static int[][] gear = new int[4][8];
    public static int g1Right = 4, g2Left = 0, g2Right = 4, g3Left = 0;
    public static int g3Right = 4, g4Left = 0;
    public static int[] res = new int[6];
    public static int[] spin = {1, -1};
    public static boolean[] vi = new boolean[4];


    public static void spinGear1(int dir) {

        boolean isCheck1 = false;
        vi[0] = true;

        if (gear[0][g1Right] != gear[1][g2Left]) {
            isCheck1 = true;
        }

        int tmp = g1Right + spin[dir];
        if (tmp < 0) tmp = 7;
        tmp %= 8;
        res[0] = tmp;

        if (!vi[1] && isCheck1) {
            if (dir == 1) dir = 0;
            else dir = 1;
            spinGear2(dir);
        }
    }

    public static void spinGear2(int dir) {

        boolean isCheck1 = false;
        boolean isCheck2 = false;
        vi[1] = true;

        if (gear[1][g2Left] != gear[0][g1Right]) {

            isCheck1 = true;
        }

        if (gear[1][g2Right] != gear[2][g3Left]) {

            isCheck2 = true;
        }


        int tmp1 = g2Left + spin[dir];
        int tmp2 = g2Right + spin[dir];

        if (tmp1 < 0) tmp1 = 7;
        tmp1 %= 8;
        if (tmp2 < 0) tmp2 = 7;
        tmp2 %= 8;
        res[1] = tmp1;
        res[2] = tmp2;
        if (dir == 1) dir = 0;
        else dir = 1;


        if (!vi[0] && isCheck1) {
            spinGear1(dir);
        }

        if (!vi[2] && isCheck2) {
            spinGear3(dir);
        }
    }

    public static void spinGear3(int dir) {

        boolean isCheck1 = false;
        boolean isCheck2 = false;
        vi[2] = true;

        if (gear[2][g3Left] != gear[1][g2Right]) {
            isCheck1 = true;
        }

        if (gear[2][g3Right] != gear[3][g4Left]) {
            isCheck2 = true;
        }

        int tmp1 = g3Left + spin[dir];
        int tmp2 = g3Right + spin[dir];
        if (tmp1 < 0) tmp1 = 7;
        tmp1 %= 8;
        if (tmp2 < 0) tmp2 = 7;
        tmp2 %= 8;

        res[3] = tmp1;
        res[4] = tmp2;

        if (dir == 1) dir = 0;
        else dir = 1;

        if (!vi[1] && isCheck1) {
            spinGear2(dir);
        }

        if (!vi[3] && isCheck2) {
            spinGear4(dir);
        }
    }

    public static void spinGear4(int dir) {

        boolean isCheck = false;
        vi[3] = true;

        int tmp = g4Left + spin[dir];
        if (tmp < 0) tmp = 7;
        tmp %= 8;
        res[5] = tmp;

        if (gear[3][g4Left] != gear[2][g3Right]) {
            isCheck = true;
        }

        if (!vi[2] && isCheck) {
            if (dir == 1) dir = 0;
            else dir = 1;
            spinGear3(dir);
        }
    }

    public static void updateGearIdx() {
        if (res[0] != -1) {
            g1Right = res[0];
        }
        if (res[1] != -1) {
            g2Left = res[1];
        }
        if (res[2] != -1) {
            g2Right = res[2];
        }
        if (res[3] != -1) {
            g3Left = res[3];
        }
        if (res[4] != -1) {
            g3Right = res[4];
        }
        if (res[5] != -1) {
            g4Left = res[5];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            String input = br.readLine(); // N = 0. S = 1;
            int idx = 2;
            for (int j = 0; j < 8; j++) {
                gear[i][idx++ % 8] = Character.getNumericValue(input.charAt(j));
            }
        }

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if (dir == -1) dir = 0;
            vi = new boolean[4];
            res = new int[6];
            Arrays.fill(res, -1);
            vi[num - 1] = true;

            if (num == 1) {
                spinGear1(dir);

            } else if (num == 2) {
                spinGear2(dir);

            } else if (num == 3) {
                spinGear3(dir);

            } else {
                spinGear4(dir);
            }

            updateGearIdx();
        }

        int answer = 0;

        g1Right -= 2;
        if (g1Right < 0) {
            g1Right = 8 + g1Right;
        }

        if (gear[0][g1Right] == 1) {
            answer += 1;
        }

        if (gear[1][(g2Left + 2) % 8] == 1) {
            answer += 2;
        }

        if (gear[2][(g3Left + 2) % 8] == 1) {
            answer += 4;
        }

        if (gear[3][(g4Left + 2) % 8] == 1) {
            answer += 8;
        }

        System.out.print(answer);
    }
}