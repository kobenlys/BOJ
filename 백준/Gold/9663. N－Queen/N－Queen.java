import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int N, cnt;
    public static boolean[] leftVi;
    public static boolean[] rightVi;
    public static boolean[] upVi;

    public static void makeQueen(int start) {

        if (N == start) {
            cnt++;
            return;
        }
        // start = y축, i = x축을 담당한다.
        for (int i = 0; i < N; i++) {
            // 좌측상단 대각선 과 우측상단 대각선 과 상단 직선이 다 False 라면?
            // 퀸의 이동 범위내에 다른 퀸이 없음
            if (!leftVi[(N - 1) - i + start] && !rightVi[start + i] && !upVi[i]) {
                leftVi[(N - 1) - i + start] = rightVi[start + i] = upVi[i] = true;
                makeQueen(start+1);
                leftVi[(N - 1) - i + start] = rightVi[start + i] = upVi[i] = false;
            }
        }
    }


    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        N = Integer.parseInt(br.readLine());
        // 이 방법은 배열을 만들지 않고 퀸의 이동범위내만 체크한다.
        leftVi = new boolean[N * 2]; // 좌측 상단 대각선 체크
        rightVi = new boolean[N * 2]; // 우측 상단 대각선 체크
        upVi = new boolean[N]; // 상단 직선 체크
        makeQueen(0);
        System.out.print(cnt);
    }
}