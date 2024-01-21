import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringBuilder sb = new StringBuilder();
    public static int x, y;
    public static String leftRow = "";
    public static String rightRow = "";
    public static String bothRow = "";
    public static String col = "";
    public static String empty = "";


    public static void makeNumber(char n, int k) {

        switch (n) {
            case '1':
                if (k == 0 || k == y / 2 || k == y - 1) {
                    sb.append(empty);
                } else {
                    sb.append(rightRow);
                }
                break;
            case '2':
                if (k == 0 || k == y / 2 || k == y - 1) {
                    sb.append(col);
                } else if (k > 0 && k < y / 2) {
                    sb.append(rightRow);
                } else {
                    sb.append(leftRow);
                }
                break;

            case '3':
                if (k == 0 || k == y / 2 || k == y - 1) {
                    sb.append(col);
                } else {
                    sb.append(rightRow);
                }
                break;

            case '4':
                if (k == 0 || k == y-1) {
                    sb.append(empty);
                } else if (k > 0 && k < y / 2) {
                    sb.append(bothRow);
                } else if (k == y / 2) {
                    sb.append(col);
                } else {
                    sb.append(rightRow);
                }
                break;

            case '5':
                if (k == 0 || k == y / 2 || k == y - 1) {
                    sb.append(col);
                } else if (k > 0 && k < y / 2) {
                    sb.append(leftRow);
                } else {
                    sb.append(rightRow);
                }
                break;

            case '6':
                if (k == 0 || k == y / 2 || k == y - 1) {
                    sb.append(col);
                } else if (k > 0 && k < y / 2) {
                    sb.append(leftRow);
                } else {
                    sb.append(bothRow);
                }
                break;

            case '7':
                if (k == 0) {
                    sb.append(col);
                } else if (k == y/2 || k == y-1){
                    sb.append(empty);
                } else {
                    sb.append(rightRow);
                }
                break;

            case '8':
                if (k == 0 || k == y / 2 || k == y - 1) {
                    sb.append(col);
                } else {
                    sb.append(bothRow);
                }
                break;

            case '9':
                if (k == 0 || k == y / 2 || k == y - 1) {
                    sb.append(col);
                } else if (k > 0 && k < y / 2) {
                    sb.append(bothRow);
                } else {
                    sb.append(rightRow);
                }
                break;

            case '0':
                if (k == 0 || k == y - 1) {
                    sb.append(col);
                } else if (k == y/2) {
                    sb.append(empty);
                } else {
                    sb.append(bothRow);
                }
                break;
        }
        sb.append(" ");
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        String str = st.nextToken();

        x = N + 2;
        y = N * 2 + 3;

        for (int i = 0; i < x; i++) {
            if (i == 0 || i == x - 1) {
                col += " ";
            } else {
                col += "-";
            }
        }

        for (int i = 0; i < x; i++) {

            if (i == x - 1) {
                rightRow += "|";
            } else {
                rightRow += " ";
            }

            if (i == 0) {
                leftRow += "|";
            } else {
                leftRow += " ";
            }

            if (i > 0 && i < x -1) {
                bothRow += " ";
            } else {
                bothRow += "|";
            }
            empty += " ";
        }

        for (int i = 0; i < N * 2 + 3; i++) {
            for (int j = 0; j < str.length(); j++) {
                makeNumber(str.charAt(j), i);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
