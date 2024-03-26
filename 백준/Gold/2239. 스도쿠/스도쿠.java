import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] arr1 = new int[9][9];
    
    // 가로, 세로 조건 검사.
    public static boolean check1(int x, int y, int num) {

        for (int i = 0; i < 9; i++) {
            if (arr1[y][i] == num || arr1[i][x] == num) {
                return false;
            }
        }
        return true;
    }
    
    // 3*3 칸 조건 검사
    public static boolean check2(int x, int y, int num) {
        // 0, 3, 6
        // 0 1 2 / 3 4 5 / 6 7 8
        int nX = (x / 3) * 3;
        int nY = (y / 3) * 3;
    
        for (int i = nY; i < nY + 3; i++) {
            for (int j = nX; j < nX + 3; j++) {
                if (arr1[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void sdoku(int x, int y) {

        // 출력.
        if (x == 9 && y == 8) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(arr1[i][j]);
                }
                sb.append("\n");
            }
            System.out.print(sb);
            System.exit(0);
        }
        // 행 바꾸기.
        if (x == 9) {
            sdoku(0, y + 1);
            return;
        }
        // 숫자를 입력해야 하는 칸 이라면
        if (arr1[y][x] == 0) {
            for (int i = 1; i <= 9; i++) {
                // check1 , check2 둘다 부합하는 숫자 넣기.
                if (!check1(x, y, i) || !check2(x, y, i)) continue;
                arr1[y][x] = i;
                sdoku(x + 1, y);
                arr1[y][x] = 0;
            }
        } else {
            // 숫자가 이미 입력되어있다면 한칸 전진.
            sdoku(x + 1, y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                arr1[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }
        // 스도쿠 함수 실행.
        sdoku(0, 0);
    }
}