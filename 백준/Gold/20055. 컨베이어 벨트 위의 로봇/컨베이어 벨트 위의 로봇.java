import java.io.*;
import java.util.*;

public class Main {
    public static int N, K;
    public static int[] arr1, db;

    // 레일 돌리기, 로봇 + 2N 레일
    public static void rollingRails() {
        for (int i = N - 1; 1 <= i; i--) {
            if (arr1[i] == 1 && arr1[i + 1] != 1) {
                arr1[i + 1] = 1;
                arr1[i] = 0;
            }
        }

        int tmpDB = db[N + N];
        for (int i = N + N - 1; 1 <= i; i--) {
            db[i + 1] = db[i];
        }
        db[1] = tmpDB;


        if (arr1[N] == 1) arr1[N] = 0;
    }

    // 로봇만 한칸 움직이기 + 내구도 감소.
    public static void moveRobot() {
        for (int i = N - 1; 1 <= i; i--) {
            if (arr1[i] == 1 && arr1[i + 1] != 1 && db[i + 1] > 0) {
                db[i + 1]--;
                arr1[i + 1] = 1;
                arr1[i] = 0;
            }
        }

        if (arr1[N] == 1) arr1[N] = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr1 = new int[N + 1];
        db = new int[N + N + 1];
        int cnt = 0;


        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N + N; i++) {
            db[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            cnt++;
            rollingRails(); // 레일 움직이기
            moveRobot(); // 로봇 움직이기 + 내구도 감소

            if (db[1] > 0 && arr1[1] == 0) { // 로봇 올리기 + 내구도 감소
                db[1]--;
                arr1[1] = 1;
            }

            int brokenRails = 0;
            for (int i = 1; i <= N + N; i++) {
                if (db[i] == 0) brokenRails++;
            }
            if (brokenRails >= K) break;
        }

        System.out.println(cnt);
    }
}