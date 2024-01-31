import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int minX, minY;
    public static int[][] arr1 = new int[19][19];
    public static ArrayList<node> site = new ArrayList<>();

    public static class node{
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean FiveInARow() {

        for (int i = 0; i < site.size(); i++) {
            node nd = site.get(i);
            if (isFive1(nd.x, nd.y, arr1[nd.y][nd.x])) {
                return true;
            } else if (isFive2(nd.x, nd.y, arr1[nd.y][nd.x])) {
                return true;
            } else if (isFive3(nd.x, nd.y, arr1[nd.y][nd.x])) {
                return true;
            } else if (isFive4(nd.x, nd.y, arr1[nd.y][nd.x])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFive1(int x, int y, int color) {
        int cnt = 1;
        minX = x;
        minY = y;
        int tmpX1 = x + 1;
        int tmpX2 = x - 1;

        for (int i = 0; i < 5; i++) {
            if (range(tmpX1, y) && arr1[y][tmpX1] == color) {
                cnt++;
                tmpX1++;
            }
            if (range(tmpX2, y) && arr1[y][tmpX2] == color) {
                cnt++;
                minX = tmpX2;
                tmpX2--;
            }
        }
        return 5 == cnt;
    }

    public static boolean isFive2(int x, int y, int color) {
        int cnt = 1;
        minX = x;
        minY = y;
        int tmpY1 = y + 1;
        int tmpY2 = y - 1;

        for (int i = 0; i < 5; i++) {
            if (range(x, tmpY1) && arr1[tmpY1][x] == color) {
                cnt++;
                tmpY1++;
            }
            if (range(x, tmpY2) && arr1[tmpY2][x] == color) {
                cnt++;
                minY = tmpY2;
                tmpY2--;
            }
        }
        return 5 == cnt;
    }

    public static boolean isFive3(int x, int y, int color) {// 우상 대각선
        int cnt = 1;
        minX = x;
        minY = y;
        int tmpX1 = x + 1;
        int tmpY1 = y - 1;
        int tmpX2 = x - 1;
        int tmpY2 = y + 1;

        for (int i = 0; i < 5; i++) {
            if (range(tmpX1, tmpY1) && arr1[tmpY1][tmpX1] == color) {
                cnt++; tmpX1++; tmpY1--;
            }
            if (range(tmpX2, tmpY2) && arr1[tmpY2][tmpX2] == color) {
                minX = tmpX2; minY = tmpY2;
                cnt++; tmpX2--; tmpY2++;
            }
        } 
        return 5 == cnt;
    }

    public static boolean isFive4(int x, int y, int color) { // 좌상 대각선
        int cnt = 1;
        minX = x;
        minY = y;
        int tmpX1 = x - 1;
        int tmpY1 = y - 1;
        int tmpX2 = x + 1;
        int tmpY2 = y + 1;

        for (int i = 0; i < 5; i++) {
            if (range(tmpX1, tmpY1) && arr1[tmpY1][tmpX1] == color) {
                minX = tmpX1; minY = tmpY1;
                cnt++; tmpX1--; tmpY1--;
            }
            if (range(tmpX2, tmpY2) && arr1[tmpY2][tmpX2] == color) {
                cnt++; tmpX2++; tmpY2++;
            }
        }
       
        return 5 == cnt;
    }

    public static boolean range(int x, int y) {
        return x >= 0 && y >= 0 && x < 19 && y < 19;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 19; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] != 0) {
                    site.add(new node(j, i));
                }
            }
        }
        if (FiveInARow()) {
            System.out.println(arr1[minY][minX]);
            System.out.println(++minY + " " + ++minX);
        } else {
            System.out.println(0);
        }
    }
}
