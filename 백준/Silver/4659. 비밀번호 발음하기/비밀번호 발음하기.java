import java.io.*;
import java.util.*;

public class Main {

    public static Set<Character> set;

    public static boolean check1(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean check2(String str) {
        int vowelsCnt = 0;
        int consonantsCnt = 0;

        for (int i = 0; i < str.length(); i++) {

            if (set.contains(str.charAt(i))) {
                vowelsCnt++;
                consonantsCnt = 0;
            } else {
                consonantsCnt++;
                vowelsCnt = 0;
            }
            if (vowelsCnt == 3 || consonantsCnt == 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean check3(String str) {
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == 'e' || str.charAt(i) == 'o') {
                continue;
            }
            if (str.charAt(i) == str.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        while (true) {

            String str = br.readLine();
            boolean isOk = false;
            if (str.equals("end")) {
                break;
            }

            if (check1(str) && check2(str) && check3(str)) {
                isOk = true;
            }

            sb.append("<").append(str).append(">");
            sb.append(" is");

            if (!isOk) {
                sb.append(" not");
            }

            sb.append(" acceptable.").append("\n");
        }

        System.out.println(sb);
    }
}