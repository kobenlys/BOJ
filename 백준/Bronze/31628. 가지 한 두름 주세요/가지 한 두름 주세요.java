import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static String[][] arr1;

    public static boolean rowCheck(int x, String str) {

        for (int i = 0; i < 10; i++) {
            if (!(arr1[i][x].equals(str))) return false;
        }
        return true;
    }

    public static boolean colCheck(int y, String str) {

        for (int i = 0; i < 10; i++) {
            if (!(arr1[y][i].equals(str))) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr1 = new String[10][10];
        int cnt = 0;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                arr1[i][j] = st.nextToken();
            }
        }

        for (int i = 0; i < 10; i++) {
            if (rowCheck(i, arr1[i][i])) {
                cnt++;
                break;
            }
            if (colCheck(i, arr1[i][i])) {
                cnt++;
                break;
            }
        }
        System.out.print(cnt);
    }
}